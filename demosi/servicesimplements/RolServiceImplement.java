package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.Rol;
import pe.edu.upc.demosi.repositories.IRolRepository;
import pe.edu.upc.demosi.servicesinterfaces.IRolService;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImplement implements IRolService {
    private final IRolRepository rR;

    public RolServiceImplement(IRolRepository rR) {
        this.rR = rR;
    }

    @Override
    public void insert(Rol r) { rR.save(r); }

    @Override
    public List<Rol> list() { return rR.findAll(); }

    @Override
    public Optional<Rol> listId(Long id) { return rR.findById(id); }

    @Override
    public void delete(Long id) { rR.deleteById(id); }
}
