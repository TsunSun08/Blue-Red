package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.Validacion;

import java.util.List;
import java.util.Optional;

public interface IValidacionService {
    public void insert(Validacion validacion);
    public List<Validacion> list();
    public Optional<Validacion> listId(Long id);
    public void delete(Long id);
    public List<Object[]> listarValidacionesRechazadasPorPescador(Long idUsuario);
}
