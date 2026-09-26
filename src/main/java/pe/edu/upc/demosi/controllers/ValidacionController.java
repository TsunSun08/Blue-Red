package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.ValidacionDTO;
import pe.edu.upc.demosi.dtos.ValidacionDTOInsert;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.entities.Validacion;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;
import pe.edu.upc.demosi.servicesinterfaces.IValidacionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/validacion")
public class ValidacionController {
    private final IValidacionService vS;
    private final ILoteCapturaService lcS;
    private final ModelMapper modelMapper;

    public ValidacionController(IValidacionService vS, ILoteCapturaService lcS, ModelMapper modelMapper) {
        this.vS = vS;
        this.lcS = lcS;
        this.modelMapper = modelMapper;
    }

    //listar
    @GetMapping
    public ResponseEntity<List<ValidacionDTO>> listar(){

        List<ValidacionDTO> lista = vS.list()
                .stream()
                .map(validacion-> modelMapper.map(validacion, ValidacionDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    //registrar una validacion
    @PostMapping
    public ResponseEntity<ValidacionDTOInsert> registrar(
            @Valid @RequestBody ValidacionDTOInsert dto) {
        LoteCaptura loteCaptura = lcS.listId(dto.getIdLoteCaptura())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un lote de captura con el ID " + dto.getIdLoteCaptura()
                        )
                );

        Validacion validacion = modelMapper.map(dto, Validacion.class);
        validacion.setLoteCaptura(loteCaptura);
        vS.insert(validacion);

        ValidacionDTOInsert responseDTO =
                modelMapper.map(validacion, ValidacionDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(validacion.getIdValidacion())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);

    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Validacion validacion = vS.listId(id)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "No existe una validacion con el ID " + id
                        )
                );
        vS.delete(validacion.getIdValidacion());
        return ResponseEntity.noContent().build();
    }
}
