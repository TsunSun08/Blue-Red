package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.EspecieDTOInsert;
import pe.edu.upc.demosi.dtos.EspecieDTOList;
import pe.edu.upc.demosi.entities.Especie;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.servicesinterfaces.IEspecieService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    //ACTUALIZAR ESPECIE
    @PutMapping
    public ResponseEntity<EspecieDTOInsert> actualizar(@Valid @RequestBody EspecieDTOInsert dto){

        Optional<Especie> existente = eS.listId(dto.getIdEspecie());

        if(existente.isEmpty()){
            throw new ResourceNotFoundException(
                    "No existe una especie con el ID: " + dto.getIdEspecie()
            );
        }

        Especie especie = existente.get();

        especie.setNombreComun(dto.getNombreComun());
        especie.setNombreCientifico(dto.getNombreCientifico());
        especie.setTallaMinima(dto.getTallaMinima());
        especie.setEnVeda(dto.isEnVeda());

        eS.update(especie);

        EspecieDTOInsert responseDTO=
                modelMapper.map(especie, EspecieDTOInsert.class);
        return ResponseEntity.ok(responseDTO);
    }

    //ELIMINAR ESPECIE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Especie es= eS.listId(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "No existe especie con el ID: " + id)
        );
        eS.delete(es.getIdEspecie());
        return ResponseEntity.noContent().build();
    }

}
