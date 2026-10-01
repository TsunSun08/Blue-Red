package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.CertificacionDTO;
import pe.edu.upc.demosi.dtos.CertificacionDTOInsert;
import pe.edu.upc.demosi.entities.Certificacion;
import pe.edu.upc.demosi.entities.Validacion;
import pe.edu.upc.demosi.exceptions.BadRequestException;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.repositories.IValidacionRepository;
import pe.edu.upc.demosi.servicesinterfaces.ICertificacionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/certificaciones")
public class CertificacionController {

    private final ICertificacionService cS;
    private final IValidacionRepository vR;
    private final ModelMapper modelMapper;

    public CertificacionController(
            ICertificacionService cS,
            IValidacionRepository vR,
            ModelMapper modelMapper) {
        this.cS = cS;
        this.vR = vR;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CertificacionDTO> registrar(
            @Valid @RequestBody CertificacionDTOInsert dto) {

        Validacion validacion = vR.findById(dto.getIdValidacion())
                .orElseThrow(() -> new BadRequestException(
                        "La validación indicada no existe."
                ));

        Certificacion certificacion =
                modelMapper.map(dto, Certificacion.class);

        certificacion.setValidacion(validacion);

        cS.insert(certificacion);

        CertificacionDTO responseDTO =
                modelMapper.map(certificacion, CertificacionDTO.class);

        responseDTO.setIdValidacion(
                certificacion.getValidacion().getIdValidacion()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(certificacion.getIdCertificacion())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CertificacionDTO>> listar() {

        List<CertificacionDTO> lista = cS.list().stream()
                .map(certificacion -> {

                    CertificacionDTO dto =
                            modelMapper.map(
                                    certificacion,
                                    CertificacionDTO.class
                            );

                    dto.setIdValidacion(
                            certificacion.getValidacion()
                                    .getIdValidacion()
                    );

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificacionDTO> listarPorId(
            @PathVariable Long id) {

        Certificacion certificacion = cS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la certificación con ID: " + id
                ));

        CertificacionDTO dto =
                modelMapper.map(
                        certificacion,
                        CertificacionDTO.class
                );

        dto.setIdValidacion(
                certificacion.getValidacion()
                        .getIdValidacion()
        );

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificacionDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CertificacionDTO dto) {

        Certificacion certificacion = cS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la certificación con ID: " + id
                ));

        Validacion validacion = vR.findById(dto.getIdValidacion())
                .orElseThrow(() -> new BadRequestException(
                        "La validación indicada no existe."
                ));

        certificacion.setValidacion(validacion);

        certificacion.setCodigoCertificado(
                dto.getCodigoCertificado()
        );

        certificacion.setFechaEmision(
                dto.getFechaEmision()
        );

        certificacion.setEstadoCertificado(
                dto.getEstadoCertificado()
        );

        cS.update(certificacion);

        CertificacionDTO responseDTO =
                modelMapper.map(
                        certificacion,
                        CertificacionDTO.class
                );

        responseDTO.setIdValidacion(
                certificacion.getValidacion()
                        .getIdValidacion()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        Certificacion certificacion = cS.listId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la certificación con ID: " + id
                ));

        cS.delete(certificacion.getIdCertificacion());

        return ResponseEntity.noContent().build();
    }
}