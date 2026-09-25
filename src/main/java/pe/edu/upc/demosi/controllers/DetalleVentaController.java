package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.DetalleVentaDTO;
import pe.edu.upc.demosi.dtos.DetalleVentaDTOInsert;
import pe.edu.upc.demosi.entities.DetalleVenta;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.exceptions.BadRequestException;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.repositories.IDocumentoVentaRepository;
import pe.edu.upc.demosi.repositories.ILoteCapturaRepository;
import pe.edu.upc.demosi.servicesinterfaces.IDetalleVentaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/detalleventas")
public class DetalleVentaController {

    private final IDetalleVentaService dS;
    private final IDocumentoVentaRepository dvR;
    private final ILoteCapturaRepository lcR;
    private final ModelMapper modelMapper;

    public DetalleVentaController(
            IDetalleVentaService dS,
            IDocumentoVentaRepository dvR,
            ILoteCapturaRepository lcR,
            ModelMapper modelMapper) {
        this.dS = dS;
        this.dvR = dvR;
        this.lcR = lcR;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaDTO>> listar() {
        List<DetalleVentaDTO> lista = dS.list()
                .stream()
                .map(d -> {
                    DetalleVentaDTO dto = modelMapper.map(d, DetalleVentaDTO.class);
                    dto.setIdDocumentoVenta(d.getDocumentoVenta().getIdDocumentoVenta());
                    dto.setIdLoteCaptura(d.getLoteCaptura().getIdLoteCaptura());
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDTO> buscarId(@PathVariable Long id) {
        DetalleVenta detalleVenta = dS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el detalle de venta con el id: " + id
                ));

        DetalleVentaDTO responseDTO =
                modelMapper.map(detalleVenta, DetalleVentaDTO.class);

        responseDTO.setIdDocumentoVenta(
                detalleVenta.getDocumentoVenta().getIdDocumentoVenta()
        );
        responseDTO.setIdLoteCaptura(
                detalleVenta.getLoteCaptura().getIdLoteCaptura()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<DetalleVentaDTO> registrar(
            @Valid @RequestBody DetalleVentaDTOInsert dto) {

        DocumentoVenta documentoVenta = dvR.findById(dto.getIdDocumentoVenta())
                .orElseThrow(() -> new BadRequestException(
                        "El documento de venta indicado no existe."
                ));

        LoteCaptura loteCaptura = lcR.findById(dto.getIdLoteCaptura())
                .orElseThrow(() -> new BadRequestException(
                        "El lote de captura indicado no existe."
                ));

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setDocumentoVenta(documentoVenta);
        detalleVenta.setLoteCaptura(loteCaptura);
        detalleVenta.setPesoComprado(dto.getPesoComprado());
        detalleVenta.setSubtotal(dto.getSubtotal());

        dS.insert(detalleVenta);

        DetalleVentaDTO responseDTO =
                modelMapper.map(detalleVenta, DetalleVentaDTO.class);

        responseDTO.setIdDocumentoVenta(
                detalleVenta.getDocumentoVenta().getIdDocumentoVenta()
        );
        responseDTO.setIdLoteCaptura(
                detalleVenta.getLoteCaptura().getIdLoteCaptura()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(detalleVenta.getIdDetalleVenta())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<DetalleVentaDTO> actualizar(
            @Valid @RequestBody DetalleVentaDTOInsert dto) {

        DetalleVenta detalleVenta = dS.listId(dto.getIdDetalleVenta())
                .orElseThrow(() -> new BadRequestException(
                        "El detalle de venta indicado no existe."
                ));

        DocumentoVenta documentoVenta = dvR.findById(dto.getIdDocumentoVenta())
                .orElseThrow(() -> new BadRequestException(
                        "El documento de venta indicado no existe."
                ));

        LoteCaptura loteCaptura = lcR.findById(dto.getIdLoteCaptura())
                .orElseThrow(() -> new BadRequestException(
                        "El lote de captura indicado no existe."
                ));

        detalleVenta.setDocumentoVenta(documentoVenta);
        detalleVenta.setLoteCaptura(loteCaptura);
        detalleVenta.setPesoComprado(dto.getPesoComprado());
        detalleVenta.setSubtotal(dto.getSubtotal());

        dS.update(detalleVenta);

        DetalleVentaDTO responseDTO =
                modelMapper.map(detalleVenta, DetalleVentaDTO.class);

        responseDTO.setIdDocumentoVenta(
                detalleVenta.getDocumentoVenta().getIdDocumentoVenta()
        );
        responseDTO.setIdLoteCaptura(
                detalleVenta.getLoteCaptura().getIdLoteCaptura()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        DetalleVenta detalleVenta = dS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el detalle de venta con el id: " + id
                ));

        dS.delete(detalleVenta.getIdDetalleVenta());

        return ResponseEntity.noContent().build();
    }
}
