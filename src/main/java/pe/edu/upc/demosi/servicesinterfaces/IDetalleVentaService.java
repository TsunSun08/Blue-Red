package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {
    void insert(DetalleVenta d);
    List<DetalleVenta> list();
    Optional<DetalleVenta> listId(Long id);
    void update(DetalleVenta d);
    void delete(Long id);
}
