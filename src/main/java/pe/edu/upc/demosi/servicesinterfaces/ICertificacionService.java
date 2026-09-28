package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.Certificacion;

import java.util.List;
import java.util.Optional;

public interface ICertificacionService {
    void insert(Certificacion c);
    List<Certificacion> list();
    Optional<Certificacion> listId(Long id);
    void delete(Long id);
}