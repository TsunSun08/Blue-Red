package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.Certificacion;
import pe.edu.upc.demosi.repositories.ICertificacionRepository;
import pe.edu.upc.demosi.servicesinterfaces.ICertificacionService;

import java.util.List;
import java.util.Optional;

@Service
public class CertificacionServiceImplement implements ICertificacionService {

    private final ICertificacionRepository cR;

    public CertificacionServiceImplement(ICertificacionRepository cR) {
        this.cR = cR;
    }

    @Override
    public void insert(Certificacion c) {
        cR.save(c);
    }

    @Override
    public List<Certificacion> list() {
        return cR.findAll();
    }

    @Override
    public Optional<Certificacion> listId(Long id) {
        return cR.findById(id);
    }

    @Override
    public void delete(Long id) {
        cR.deleteById(id);
    }
}