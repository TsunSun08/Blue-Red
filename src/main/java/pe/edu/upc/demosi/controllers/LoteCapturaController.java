package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.*;
import pe.edu.upc.demosi.entities.Especie;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.servicesinterfaces.IEspecieService;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lote-captura")
@PreAuthorize("hasRole('ADMIN') OR hasRole('PESCADOR')")
public class LoteCapturaController {

    private final ILoteCapturaService lcS;
    private final IEspecieService eS;
    private final IUsuarioService uS;
    private final ModelMapper modelMapper;

    public LoteCapturaController(
            ILoteCapturaService lcS,
            IEspecieService eS,
            IUsuarioService uS,
            ModelMapper modelMapper) {
        this.lcS = lcS;
        this.eS = eS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }

    // HU18 - Buscar datos de pesca por ID
    @GetMapping("/{id}")
    public ResponseEntity<LoteCapturaDTO> buscarPorId(@PathVariable Long id) {

        LoteCapturaDTO dto = lcS.buscarPorId(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Listar
    @GetMapping
    public ResponseEntity<List<LoteCapturaDTO>> listar() {

        List<LoteCapturaDTO> lista = lcS.list()
                .stream()
                .map(lc -> modelMapper.map(lc, LoteCapturaDTO.class))
                .toList();

        return ResponseEntity.ok(lista);
    }

    // HU19 - Actualizar lote de captura por ID
    @PutMapping("/{id}")
    public ResponseEntity<LoteCapturaDTOInsert> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody LoteCapturaDTOInsert dto) {

        LoteCaptura loteCaptura = lcS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lote de captura no encontrado"
                        )
                );

        Usuario usuario = uS.listId(dto.getIdUsuario())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );
        Especie especie = eS.listId(dto.getIdEspecie())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Especie no encontrada"
                        )
                );

        loteCaptura.setFechaCaptura(dto.getFechaCaptura());
        loteCaptura.setLatitud(dto.getLatitud());
        loteCaptura.setLongitud(dto.getLongitud());
        loteCaptura.setCantidadPeces(dto.getCantidadPeces());
        loteCaptura.setPesoTotal(dto.getPesoTotal());
        loteCaptura.setImagenReferencia(dto.getImagenReferencia());
        loteCaptura.setPrecioLote(dto.getPrecioLote());

        lcS.actualizar(loteCaptura);
        LoteCapturaDTOInsert responseDTO =
                modelMapper.map(loteCaptura, LoteCapturaDTOInsert.class);

        responseDTO.setIdLoteCaptura(
                loteCaptura.getIdLoteCaptura()
        );

        responseDTO.setIdUsuario(
                loteCaptura.getUsuario().getIdUsuario()
        );

        responseDTO.setIdEspecie(
                loteCaptura.getEspecie().getIdEspecie()
        );

        return ResponseEntity.ok(responseDTO);
    }

    // HU20 - Eliminar lote de captura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        lcS.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // HU37 - Consultar datos de pesca
    @GetMapping("/consultar")
    public ResponseEntity<List<LoteCapturaDTO>> consultarDatosPesca(
            @RequestParam(required = false) String estado) {

        List<LoteCapturaDTO> lista = lcS.consultarDatosPesca(estado)
                .stream()
                .map(lc -> modelMapper.map(lc, LoteCapturaDTO.class))
                .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No se encontraron registros de pesca que coincidan con la consulta."
            );
        }

        return ResponseEntity.ok(lista);
    }

    // Registrar un lote de captura
    @PostMapping
    public ResponseEntity<LoteCapturaDTOInsert> registrar(
            @Valid @RequestBody LoteCapturaDTOInsert dto) {

        Usuario usuario = uS.listId(dto.getIdUsuario())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el usuario con el ID " + dto.getIdUsuario()
                        )
                );

        Especie especie = eS.listId(dto.getIdEspecie())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la especie con el ID " + dto.getIdEspecie()
                        )
                );

        LoteCaptura lc = modelMapper.map(dto, LoteCaptura.class);

        lc.setUsuario(usuario);
        lc.setEspecie(especie);
        lc.setEstado("Pendiente");
        lcS.insert(lc);
        LoteCapturaDTOInsert responseDTO =
                modelMapper.map(lc, LoteCapturaDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lc.getIdLoteCaptura())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // Query HU40
    @GetMapping("/historial-por-usuario/{idUsuario}")
    public ResponseEntity<List<HistorialLoteCapturaPorUsuarioDTO>>
    obtenerHistorialPorUsuario(@PathVariable Long idUsuario) {

        List<HistorialLoteCapturaPorUsuarioDTO> lista =
                lcS.listarLoteCapturaDeUnUsuario(idUsuario)
                        .stream()
                        .map(item -> {

                            HistorialLoteCapturaPorUsuarioDTO dto =
                                    new HistorialLoteCapturaPorUsuarioDTO();

                            dto.setIdLoteCaptura(
                                    ((Number) item[0]).longValue()
                            );

                            dto.setEspecie(
                                    (String) item[1]
                            );

                            dto.setFechaCaptura(
                                    (LocalDate) item[2]
                            );

                            dto.setCantidadPeces(
                                    ((Number) item[3]).intValue()
                            );

                            dto.setPesoTotal(
                                    ((Number) item[4]).floatValue()
                            );

                            dto.setPrecioLote(
                                    ((Number) item[5]).floatValue()
                            );

                            dto.setEstado(
                                    (String) item[6]
                            );

                            return dto;
                        })
                        .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "El usuario con ID " + idUsuario
                            + " no tiene lotes de captura registrados."
            );
        }

        return ResponseEntity.ok(lista);
    }

    // HU44
    @GetMapping("/buscar-por-especie")
    public ResponseEntity<List<BuscarLoteEspecieDTO>>
    buscarPorEspecie(@RequestParam String nombre) {

        if (nombre == null || nombre.trim().length() < 3) {
            throw new ResourceNotFoundException(
                    "El nombre de la especie no puede estar vacío."
            );
        }

        List<BuscarLoteEspecieDTO> lista =
                lcS.buscarLotesPorNombreEspecie(nombre)
                        .stream()
                        .map(item -> {

                            BuscarLoteEspecieDTO dto =
                                    new BuscarLoteEspecieDTO();
                            dto.setEspecie(
                                    (String) item[0]
                            );
                            dto.setCantidadDisponible(
                                    ((Number) item[1]).intValue()
                            );
                            dto.setFechaCaptura(
                                    (LocalDate) item[2]
                            );
                            dto.setLatitud(
                                    ((Number) item[3]).floatValue()
                            );
                            dto.setLongitud(
                                    ((Number) item[4]).floatValue()
                            );
                            dto.setProveedor(
                                    (String) item[5]
                            );
                            dto.setEstado(
                                    (String) item[6]
                            );
                            return dto;
                        })
                        .toList();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No se encontraron lotes disponibles para la especie: "
                            + nombre
            );
        }
        return ResponseEntity.ok(lista);
    }
}