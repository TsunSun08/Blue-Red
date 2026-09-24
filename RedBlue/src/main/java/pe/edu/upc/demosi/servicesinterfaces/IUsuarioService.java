package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    void insert (Usuario u);
    List<Usuario> list();
    Optional<Usuario> listId(Long idUsuario);
    void update(Usuario usuario);
}
