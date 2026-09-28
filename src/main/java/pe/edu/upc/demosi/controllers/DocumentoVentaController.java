package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.exceptions.BadRequestException;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/documento-venta")
public class DocumentoVentaController {
    private final IDocumentoVentaService docvS;
    private final IUsuarioService uS;
    private final ModelMapper modelMapper;

    public DocumentoVentaController(IDocumentoVentaService docvS, IUsuarioService uS, ModelMapper modelMapper) {
        this.docvS = docvS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }


    //Query 46
    @GetMapping("/costo-total-general/{idUsuario}")
    public ResponseEntity<Double> costoTotalGeneralPorUsuario(@PathVariable Long idUsuario, @RequestParam LocalDate fechaInicio,@RequestParam LocalDate fechaFin){
        Double costoTotal = docvS.costoTotalGeneralPorUsuario(
                idUsuario,
                fechaInicio,
                fechaFin
        );
        return ResponseEntity.ok(costoTotal);
    }

    //HU14
    @PutMapping
    public ResponseEntity<DocumentoVentaDTO> actualizar(@Valid @RequestBody DocumentoVentaDTOInsert dto) {

        DocumentoVenta documentoVenta = docvS.listId(dto.getIdDocumentoVenta())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el documento de venta con el id: " + dto.getIdDocumentoVenta()
                ));

        Usuario usuario = uS.listId(dto.getIdUsuario())
                .orElseThrow(() -> new BadRequestException(
                        "El usuario indicado no existe."
                ));

        documentoVenta.setUsuario(usuario);
        documentoVenta.setFechaEmision(dto.getFechaEmision());
        documentoVenta.setMontoTotal(dto.getMontoTotal());
        documentoVenta.setTipoDocumento(dto.getTipoDocumento());

        docvS.update(documentoVenta);

        DocumentoVentaDTO responseDTO = modelMapper.map(documentoVenta, DocumentoVentaDTO.class);
        responseDTO.setIdUsuario(documentoVenta.getUsuario().getIdUsuario());

        return ResponseEntity.ok(responseDTO);
    }

    //HU15
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        DocumentoVenta documentoVenta = docvS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el documento de venta con el id: " + id
                ));

        docvS.delete(documentoVenta.getIdDocumentoVenta());

        return ResponseEntity.noContent().build();
    }
}
