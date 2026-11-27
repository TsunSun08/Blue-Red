package pe.edu.upc.demosi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.demosi.dtos.LoginRequestDTO;
import pe.edu.upc.demosi.dtos.LoginResponseDTO;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;
import pe.edu.upc.demosi.securities.JwtTokenService;

import java.util.List;

@RestController
@RequestMapping("/login")
//@PreAuthorize("permitAll()")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final IUsuarioRepository uR;

    public LoginController(
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService, IUsuarioRepository uR) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.uR = uR;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasena()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtTokenService.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Usuario usuario = uR.findByCorreo(userDetails.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));

        return ResponseEntity.ok(
                new LoginResponseDTO(
                        token,
                        userDetails.getUsername(),
                        roles,
                        usuario.getIdUsuario()


                )
        );
    }
}