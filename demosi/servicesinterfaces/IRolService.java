package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    void insert(Rol r);
    List<Rol> list();
    Optional<Rol> listId(Long id);
    void delete(Long id);
}
