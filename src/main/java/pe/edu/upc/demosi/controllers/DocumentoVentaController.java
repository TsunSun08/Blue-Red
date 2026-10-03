package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;

import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.entities.Usuario;

import pe.edu.upc.demosi.exceptions.BadRequestException;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;

import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/documento-venta")
public class DocumentoVentaController {

    private final IDocumentoVentaService docvS;
    private final IUsuarioService uS;
    private final ModelMapper modelMapper;

    public DocumentoVentaController(
            IDocumentoVentaService docvS,
            IUsuarioService uS,
            ModelMapper modelMapper) {

        this.docvS = docvS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }

    // HU11 - Registrar documento de venta
    @PostMapping
    public ResponseEntity<DocumentoVentaDTO> registrar(
            @Valid @RequestBody DocumentoVentaDTOInsert dto) {

        DocumentoVenta documentoVenta = docvS.registrar(dto);

        DocumentoVentaDTO responseDTO = new DocumentoVentaDTO();

        responseDTO.setIdDocumentoVenta(
                documentoVenta.getIdDocumentoVenta()
        );

        responseDTO.setIdUsuario(
                documentoVenta.getUsuario().getIdUsuario()
        );

        responseDTO.setFechaEmision(
                documentoVenta.getFechaEmision()
        );

        responseDTO.setMontoTotal(
                documentoVenta.getMontoTotal()
        );

        responseDTO.setTipoDocumento(
                documentoVenta.getTipoDocumento()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    // HU12 - Listar documentos de venta
    @GetMapping
    public ResponseEntity<List<DocumentoVentaDTO>> listar() {

        List<DocumentoVenta> documentos = docvS.listar();

        List<DocumentoVentaDTO> listaDTO = documentos.stream().map(documento -> {

            DocumentoVentaDTO dto = new DocumentoVentaDTO();

            dto.setIdDocumentoVenta(documento.getIdDocumentoVenta());
            dto.setIdUsuario(documento.getUsuario().getIdUsuario());
            dto.setFechaEmision(documento.getFechaEmision());
            dto.setMontoTotal(documento.getMontoTotal());
            dto.setTipoDocumento(documento.getTipoDocumento());

            return dto;

        }).toList();

        return ResponseEntity.ok(listaDTO);
    }

    // HU13 - Buscar documento de venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoVentaDTO> buscarPorId(
            @PathVariable Long id) {

        DocumentoVenta documentoVenta = docvS.buscarPorId(id);

        DocumentoVentaDTO dto = new DocumentoVentaDTO();

        dto.setIdDocumentoVenta(documentoVenta.getIdDocumentoVenta());
        dto.setIdUsuario(documentoVenta.getUsuario().getIdUsuario());
        dto.setFechaEmision(documentoVenta.getFechaEmision());
        dto.setMontoTotal(documentoVenta.getMontoTotal());
        dto.setTipoDocumento(documentoVenta.getTipoDocumento());

        return ResponseEntity.ok(dto);
    }

    // HU45 - Consultar costo total de compras por especie
    @GetMapping("/costo-compras-especie/{idRestaurante}")
    public ResponseEntity<List<CostoCompraEspecieDTO>> obtenerCostoComprasPorEspecie(
            @PathVariable Long idRestaurante,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        List<CostoCompraEspecieDTO> resultado =
                docvS.obtenerCostoComprasPorEspecie(
                        idRestaurante,
                        fechaInicio,
                        fechaFin
                );

        return ResponseEntity.ok(resultado);
    }

    // Query 46
    @GetMapping("/costo-total-general/{idUsuario}")
    public ResponseEntity<Double> costoTotalGeneralPorUsuario(
            @PathVariable Long idUsuario,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        Double costoTotal =
                docvS.costoTotalGeneralPorUsuario(
                        idUsuario,
                        fechaInicio,
                        fechaFin
                );

        return ResponseEntity.ok(costoTotal);
    }

    // HU14 - Actualizar documento de venta
    @PutMapping
    public ResponseEntity<DocumentoVentaDTO> actualizar(
            @Valid @RequestBody DocumentoVentaDTOInsert dto) {

        DocumentoVenta documentoVenta =
                docvS.listId(dto.getIdDocumentoVenta())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No existe el documento de venta con el id: "
                                                + dto.getIdDocumentoVenta()
                                )
                        );

        Usuario usuario =
                uS.listId(dto.getIdUsuario())
                        .orElseThrow(() ->
                                new BadRequestException(
                                        "El usuario indicado no existe."
                                )
                        );

        documentoVenta.setUsuario(usuario);
        documentoVenta.setFechaEmision(dto.getFechaEmision());
        documentoVenta.setMontoTotal(dto.getMontoTotal());
        documentoVenta.setTipoDocumento(dto.getTipoDocumento());

        docvS.update(documentoVenta);

        DocumentoVentaDTO responseDTO =
                modelMapper.map(
                        documentoVenta,
                        DocumentoVentaDTO.class
                );

        responseDTO.setIdUsuario(
                documentoVenta.getUsuario().getIdUsuario()
        );

        return ResponseEntity.ok(responseDTO);
    }

    // HU15 - Eliminar documento de venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        DocumentoVenta documentoVenta =
                docvS.listId(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No existe el documento de venta con el id: " + id
                                )
                        );

        docvS.delete(
                documentoVenta.getIdDocumentoVenta()
        );

        return ResponseEntity.noContent().build();
    }
}