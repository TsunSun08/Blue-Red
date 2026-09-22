package pe.edu.upc.demosi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.demosi.dtos.LoginRequestDTO;
import pe.edu.upc.demosi.dtos.LoginResponseDTO;
import pe.edu.upc.demosi.securities.JwtTokenService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public LoginController(
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        // Spring Security valida internamente si el correo y la contraseña son correctos
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasena()
                )
        );

        // Si es correcto, extrae los datos del usuario validado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Genera el token JWT
        String token = jwtTokenService.generateToken(userDetails);

        // Retorna el token y el correo al frontend (o a Swagger/Postman)
        return ResponseEntity.ok(
                new LoginResponseDTO(
                        token,
                        userDetails.getUsername())
        );
    }
}
