package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.EspecieDTOInsert;
import pe.edu.upc.demosi.dtos.EspecieDTOList;
import pe.edu.upc.demosi.entities.Especie;
import pe.edu.upc.demosi.servicesinterfaces.IEspecieService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/especies")
public class EspecieController {
    private final IEspecieService eS;
    private final ModelMapper modelMapper;

    public EspecieController(IEspecieService eS, ModelMapper modelMapper) {
        this.eS = eS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<EspecieDTOList>> listar(){
        List<EspecieDTOList> lista = eS.list()
                .stream()
                .map(e -> modelMapper.map(e,EspecieDTOList.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<EspecieDTOInsert> registrar(
            @Valid @RequestBody EspecieDTOInsert dto) {
        Especie especie = modelMapper.map(dto, Especie.class);
        eS.insert(especie);

        EspecieDTOInsert responseDTO =
                modelMapper.map(especie, EspecieDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(especie.getIdEspecie())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @GetMapping("/veda")
    public ResponseEntity<List<EspecieDTOList>> buscarPorVeda(@RequestParam boolean estado) {
        List<EspecieDTOList> lista = eS.searchByVeda(estado)
                .stream()
                .map(e -> modelMapper.map(e, EspecieDTOList.class))
                .toList();
        return ResponseEntity.ok(lista);
    }
}
