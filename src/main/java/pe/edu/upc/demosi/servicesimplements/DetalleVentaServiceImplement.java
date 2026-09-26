package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.DetalleVenta;
import pe.edu.upc.demosi.repositories.IDetalleVentaRepository;
import pe.edu.upc.demosi.servicesinterfaces.IDetalleVentaService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImplement implements IDetalleVentaService {

    private final IDetalleVentaRepository dR;

    public DetalleVentaServiceImplement(IDetalleVentaRepository dR) {
        this.dR = dR;
    }

    @Override
    public void insert(DetalleVenta d) {
        dR.save(d);
    }

    @Override
    public List<DetalleVenta> list() {
        return dR.findAll();
    }

    @Override
    public Optional<DetalleVenta> listId(Long id) {
        return dR.findById(id);
    }

    @Override
    public void update(DetalleVenta d) {
        dR.save(d);
    }

    @Override
    public void delete(Long id) {
        dR.deleteById(id);
    }
}
