package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.ValidacionDTO;
import pe.edu.upc.demosi.dtos.ValidacionDTOInsert;
import pe.edu.upc.demosi.dtos.ValidacionRechazadaDTO;
import pe.edu.upc.demosi.dtos.ValidacionAceptadaDTO;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.entities.Validacion;
import pe.edu.upc.demosi.exceptions.BadRequestException;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.repositories.ICertificacionRepository;
import pe.edu.upc.demosi.servicesinterfaces.ICertificacionService;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;
import pe.edu.upc.demosi.servicesinterfaces.IValidacionService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/validacion")
@PreAuthorize("hasRole('ADMIN')")
public class ValidacionController {

    private final IValidacionService vS;
    private final ILoteCapturaService lcS;
    private final IUsuarioService uS;
    private final ICertificacionService cS;
    private final ModelMapper modelMapper;

    public ValidacionController(
            IValidacionService vS,
            ILoteCapturaService lcS,
            IUsuarioService uS, ICertificacionService cS,
            ModelMapper modelMapper) {
        this.vS = vS;
        this.lcS = lcS;
        this.uS = uS;
        this.cS = cS;
        this.modelMapper = modelMapper;
    }

    //listar
    @GetMapping
    public ResponseEntity<List<ValidacionDTO>> listar() {

        List<ValidacionDTO> lista = vS.list()
                .stream()
                .map(validacion ->
                        modelMapper.map(validacion, ValidacionDTO.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    //HU35 - buscar una validacion por ID
    @GetMapping("/{id}")
    public ResponseEntity<ValidacionDTO> buscarPorId(@PathVariable Long id) {

        Validacion validacion = vS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una validacion con el ID " + id
                        )
                );

        ValidacionDTO dto = modelMapper.map(validacion, ValidacionDTO.class);

        return ResponseEntity.ok(dto);
    }

    //registrar una validacion
    @PostMapping
    public ResponseEntity<ValidacionDTOInsert> registrar(
            @Valid @RequestBody ValidacionDTOInsert dto) {

        LoteCaptura loteCaptura = lcS.listId(dto.getIdLoteCaptura())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un lote de captura con el ID "
                                        + dto.getIdLoteCaptura()
                        )
                );
        if (vS.verificarAsociacionConUnLoteCaptura(dto.getIdLoteCaptura())) {
            throw new BadRequestException(
                    "El lote de captura con ID " + dto.getIdLoteCaptura()
                            + " ya tiene una validación registrada.");
        }

        Validacion validacion =
                modelMapper.map(dto, Validacion.class);

        validacion.setLoteCaptura(loteCaptura);

        vS.insert(validacion);

        //para actualizar el estado del lote de captura según el resultado de la validación
        if ("Aceptado".equalsIgnoreCase(dto.getResultado())) {
            loteCaptura.setEstado("Aceptado");
        } else if ("Rechazado".equalsIgnoreCase(dto.getResultado())) {
            loteCaptura.setEstado("Rechazado");
        }
        lcS.insert(loteCaptura);

        ValidacionDTOInsert responseDTO =
                modelMapper.map(validacion, ValidacionDTOInsert.class);

        responseDTO.setIdValidacion(
                validacion.getIdValidacion()
        );

        responseDTO.setIdLoteCaptura(
                validacion.getLoteCaptura().getIdLoteCaptura()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(validacion.getIdValidacion())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    //HU34 - actualizar una validacion
    @PutMapping("/{id}")
    public ResponseEntity<ValidacionDTOInsert> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ValidacionDTOInsert dto) {

        Validacion validacion = vS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una validacion con el ID " + id
                        )
                );

        validacion.setEspecieDetectada(
                dto.getEspecieDetectada()
        );

        validacion.setCantidadAnalizada(
                dto.getCantidadAnalizada()
        );

        validacion.setLongitudMinimaDetectada(
                dto.getLongitudMinimaDetectada()
        );

        validacion.setPorcentajeConfianza(
                dto.getPorcentajeConfianza()
        );

        validacion.setPorcentajeCumplimiento(
                dto.getPorcentajeCumplimiento()
        );

        validacion.setObjetoReferenciaDetectado(
                dto.getObjetoReferenciaDetectado()
        );

        validacion.setResultado(
                dto.getResultado()
        );

        validacion.setFechaValidacion(
                dto.getFechaValidacion()
        );

        vS.update(validacion);

        //para actualizar el estado del lote de captura según el resultado de la validación
        if ("Aceptado".equalsIgnoreCase(dto.getResultado())) {
            validacion.getLoteCaptura().setEstado("Aceptado");
        } else if ("Rechazado".equalsIgnoreCase(dto.getResultado())) {
            validacion.getLoteCaptura().setEstado("Rechazado");
        }
        lcS.actualizar(validacion.getLoteCaptura());

        ValidacionDTOInsert responseDTO =
                modelMapper.map(validacion, ValidacionDTOInsert.class);

        responseDTO.setIdValidacion(
                validacion.getIdValidacion()
        );

        responseDTO.setIdLoteCaptura(
                validacion.getLoteCaptura().getIdLoteCaptura()
        );

        return ResponseEntity.ok(responseDTO);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        Validacion validacion = vS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una validacion con el ID " + id
                        )
                );
        if (cS.verificarAsociacionConValidacion(id)){
            throw new BadRequestException(
                    "No se puede eliminar la validación con ID " + id
                            + " porque ya tiene un certificado asociado");
        }

        vS.delete(validacion.getIdValidacion());

        return ResponseEntity.noContent().build();
    }

    //HU42
    @GetMapping("/rechazadas-por-pescador/{idUsuario}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PESCADOR')")
    public ResponseEntity<List<ValidacionRechazadaDTO>>
    listarRechazadasPorPescador(
            @PathVariable Long idUsuario) {

        Usuario usuario = uS.listId(idUsuario)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un usuario con el id: "
                                        + idUsuario
                        )
                );

        List<ValidacionRechazadaDTO> lista =
                vS.listarValidacionesRechazadasPorPescador(
                                usuario.getIdUsuario()
                        )
                        .stream()
                        .map(item -> {
                            ValidacionRechazadaDTO dto =
                                    new ValidacionRechazadaDTO();

                            dto.setEspecieDetectada(
                                    (String) item[0]
                            );

                            dto.setPorcentajeCumplimiento(
                                    ((Number) item[1]).floatValue()
                            );

                            dto.setFechaValidacion(
                                    (LocalDate) item[2]
                            );

                            return dto;
                        })
                        .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "El pescador con id " + idUsuario
                            + " no tiene validaciones rechazadas."
            );
        }

        return ResponseEntity.ok(lista);
    }
    //HU43
    @GetMapping("/aceptadas-por-pescador/{idUsuario}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('PESCADOR')")
    public ResponseEntity<List<ValidacionAceptadaDTO>>
    listarAceptadasPorPescador(
            @PathVariable Long idUsuario) {

        Usuario usuario = uS.listId(idUsuario)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un usuario con el id: "
                                        + idUsuario
                        )
                );

        List<ValidacionAceptadaDTO> lista =
                vS.listarValidacionesAceptadasPorPescador(
                                usuario.getIdUsuario()
                        )
                        .stream()
                        .map(item -> {
                            ValidacionAceptadaDTO dto =
                                    new ValidacionAceptadaDTO();

                            dto.setEspecieDetectada(
                                    (String) item[0]
                            );

                            dto.setPorcentajeCumplimiento(
                                    ((Number) item[1]).floatValue()
                            );

                            dto.setFechaValidacion(
                                    (LocalDate) item[2]
                            );

                            return dto;
                        })
                        .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "El pescador con id " + idUsuario
                            + " no tiene validaciones aceptadas."
            );
        }

        return ResponseEntity.ok(lista);
    }
}