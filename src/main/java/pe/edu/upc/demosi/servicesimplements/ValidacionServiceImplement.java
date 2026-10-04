package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.Validacion;
import pe.edu.upc.demosi.repositories.IValidacionRepository;
import pe.edu.upc.demosi.servicesinterfaces.IValidacionService;

import java.util.List;
import java.util.Optional;

@Service
public class ValidacionServiceImplement implements IValidacionService {

    private final IValidacionRepository vR;

    public ValidacionServiceImplement(IValidacionRepository vR) {
        this.vR = vR;
    }

    @Override
    public void insert(Validacion validacion) {
        vR.save(validacion);
    }

    @Override
    public List<Validacion> list() {
        return vR.findAll();
    }

    @Override
    public Optional<Validacion> listId(Long id) {
        return vR.findById(id);
    }

    @Override
    public void update(Validacion validacion) {
        vR.save(validacion);
    }

    @Override
    public void delete(Long id) {
        vR.deleteById(id);
    }

    @Override
    public List<Object[]> listarValidacionesRechazadasPorPescador(Long idUsuario) {
        return vR.listarValidacionesRechazadasPorPescador(idUsuario);
    }

    //HU43
    @Override
    public List<Object[]> listarValidacionesAceptadasPorPescador(Long idUsuario) {
        return vR.listarValidacionesAceptadasPorPescador(idUsuario);
    }

    @Override
    public boolean verificarAsociacionConUnLoteCaptura(Long idLoteCaptura) {
        return vR.existsByLoteCaptura_IdLoteCaptura(idLoteCaptura);
    }
}