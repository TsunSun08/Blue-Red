package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.BuscarLoteEspecieDTO;
import pe.edu.upc.demosi.dtos.HistorialLoteCapturaPorUsuarioDTO;
import pe.edu.upc.demosi.dtos.LoteCapturaDTO;
import pe.edu.upc.demosi.dtos.LoteCapturaDTOInsert;
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

@RestController
@RequestMapping("/api/lote-captura")
public class LoteCapturaController {
    private final ILoteCapturaService lcS;
    private final IEspecieService eS;
    private final IUsuarioService uS;
    private final ModelMapper modelMapper;


    public LoteCapturaController(ILoteCapturaService lcS, IEspecieService eS, IUsuarioService uS, ModelMapper modelMapper) {
        this.lcS = lcS;
        this.eS = eS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }

    //listar
    @GetMapping
    public ResponseEntity<List<LoteCapturaDTO>> listar(){
        List<LoteCapturaDTO> lista = lcS.list()
                .stream()
                .map(lc -> modelMapper.map(lc, LoteCapturaDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    //Registar un lote de captura
    @PostMapping
    public ResponseEntity<LoteCapturaDTOInsert> registrar (
            @Valid @RequestBody LoteCapturaDTOInsert dto){
        Usuario usuario = uS.listId(dto.getIdUsuario())
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "No existe el usuario con el ID "+dto.getIdUsuario()
                        )
                );

        Especie especie = eS.listId(dto.getIdEspecie())
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "No existe la especie con el ID "+dto.getIdEspecie()
                        )
                );

        LoteCaptura lc = modelMapper.map(dto, LoteCaptura.class);
        lc.setUsuario(usuario);
        lc.setEspecie(especie);
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

    //Query HU40
    @GetMapping("/historial-por-usuario/{idUsuario}")
    public ResponseEntity<List<HistorialLoteCapturaPorUsuarioDTO>> obtenerHistorialPorUsuario(@PathVariable Long idUsuario){
        List<HistorialLoteCapturaPorUsuarioDTO> lista = lcS.listarLoteCapturaDeUnUsuario(idUsuario)
                .stream()
                .map(item->{
                    HistorialLoteCapturaPorUsuarioDTO dto = new HistorialLoteCapturaPorUsuarioDTO();

                    dto.setIdLoteCaptura(((Number) item[0]).longValue());
                    dto.setEspecie((String) item[1]);
                    dto.setFechaCaptura((LocalDate) item[2]);
                    dto.setCantidadPeces(((Number) item[3]).intValue());
                    dto.setPesoTotal(((Number) item[4]).floatValue());
                    dto.setPrecioLote(((Number) item[5]).floatValue());
                    dto.setEstado((String) item[6]);
                    return dto;
                })
                .toList();
        if (lista.isEmpty()){
            throw new ResourceNotFoundException(
                    "El usuario con ID " + idUsuario + " no tiene lotes de captura registrados."
            );
        }
        return ResponseEntity.ok(lista);
    }

    //HU44
    @GetMapping("/buscar-por-especie")
    public ResponseEntity<List<BuscarLoteEspecieDTO>> buscarPorEspecie(@RequestParam String nombre) {
        if (nombre == null || nombre.trim().length() < 3) {
            throw new ResourceNotFoundException("El nombre de la especie no puede estar vacío.");
        }
        List<BuscarLoteEspecieDTO> lista = lcS.buscarLotesPorNombreEspecie(nombre)
                .stream()
                .map(item -> {
                    BuscarLoteEspecieDTO dto = new BuscarLoteEspecieDTO();
                    dto.setEspecie((String) item[0]);
                    dto.setCantidadDisponible(((Number) item[1]).intValue());
                    dto.setFechaCaptura((LocalDate) item[2]);
                    dto.setLatitud(((Number) item[3]).floatValue());
                    dto.setLongitud(((Number) item[4]).floatValue());
                    dto.setProveedor((String) item[5]);
                    dto.setEstado((String) item[6]);
                    return dto;
    })
                .toList();
        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron lotes disponibles para la especie: " + nombre);
        }
        return ResponseEntity.ok(lista);
    }
}
