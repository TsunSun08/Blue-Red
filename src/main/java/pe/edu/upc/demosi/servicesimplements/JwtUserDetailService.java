package pe.edu.upc.demosi.servicesimplements;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;

import java.util.List;

@Service
public class JwtUserDetailService implements UserDetailsService {
    private final IUsuarioRepository usuarioRepository;

    public JwtUserDetailService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario)
            throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado: " + nombreUsuario
                        )
                );
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
                .map(authority -> (GrantedAuthority) authority)
                .toList();

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasena())
                .authorities(authorities)
                .disabled(!Boolean.TRUE.equals(usuario.getEnabled()))
                .build();
    }
}
