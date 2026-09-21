package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    void insert (Usuario u);
    List<Usuario> list();
}
