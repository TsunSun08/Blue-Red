package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demosi.dtos.UsuarioDTOInsert;
import pe.edu.upc.demosi.entities.Rol;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.servicesinterfaces.IRolService;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;

import java.net.URI;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final IUsuarioService uS;
    private final IRolService rS;
    private final ModelMapper modelMapper;

    public UsuarioController(IUsuarioService uS, IRolService rS, ModelMapper modelMapper) {
        this.uS = uS;
        this.rS = rS;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTOInsert> registrar(
            @Valid @RequestBody UsuarioDTOInsert dto){
        Rol rol = rS.listId(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("No existe el rol con id: " + dto.getIdRol()));
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setRol(rol);
        uS.insert(usuario);

        UsuarioDTOInsert responseDTO = modelMapper.map(usuario, UsuarioDTOInsert.class);
        responseDTO.setIdRol(rol.getIdRol());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getIdUsuario())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }
}
