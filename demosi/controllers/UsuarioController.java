package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
    private final PasswordEncoder passwordEncoder; // Inyectamos el encriptador

    public UsuarioController(IUsuarioService uS, IRolService rS, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.uS = uS;
        this.rS = rS;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTOInsert> registrar(@Valid @RequestBody UsuarioDTOInsert dto){
        Rol rol = rS.listId(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el rol con id: " + dto.getIdRol()));

        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setRol(rol);


        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        uS.insert(usuario);

        UsuarioDTOInsert responseDTO = modelMapper.map(usuario, UsuarioDTOInsert.class);
        responseDTO.setIdRol(rol.getIdRol());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario.getIdUsuario()).toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTOInsert> actualizar(@Valid @RequestBody UsuarioDTOInsert dto) {

        // 1. Verificar que el usuario exista en la base de datos
        Usuario existente = uS.listId(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe un usuario con el id: " + dto.getIdUsuario()
                ));

        // 2. Verificar que el rol asignado exista
        Rol rol = rS.listId(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el rol con id: " + dto.getIdRol()
                ));

        // 3. Mapear los datos del DTO a la entidad Usuario
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setRol(rol);

        // 4. Lógica de seguridad para la contraseña
        // Si el DTO trae una contraseña nueva, la encriptamos. Si no, mantenemos la anterior.
        if (dto.getContrasena() != null && !dto.getContrasena().isBlank()) {
            usuario.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        } else {
            usuario.setContrasena(existente.getContrasena());
        }

        // 5. Guardar los cambios mediante el servicio
        uS.update(usuario);

        // 6. Convertir la entidad actualizada de vuelta a DTO para la respuesta
        UsuarioDTOInsert responseDTO = modelMapper.map(usuario, UsuarioDTOInsert.class);
        responseDTO.setIdRol(rol.getIdRol());

        return ResponseEntity.ok(responseDTO);
    }
}
