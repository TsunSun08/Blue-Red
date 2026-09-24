package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.Especie;

import java.util.List;
import java.util.Optional;

public interface IEspecieService {
    public void insert(Especie e);
    public List<Especie> list();
    public List<Especie> searchByVeda(boolean veda);
    public Optional<Especie> listId(Long id);
    public void delete(Long id);
}
