package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.Especie;
import pe.edu.upc.demosi.repositories.IEspecieRepository;
import pe.edu.upc.demosi.servicesinterfaces.IEspecieService;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieServiceImplement implements IEspecieService {
    private final IEspecieRepository eR;

    public EspecieServiceImplement(IEspecieRepository eR) {
        this.eR = eR;
    }

    @Override
    public void insert(Especie e) { eR.save(e); }

    @Override
    public List<Especie> list() { return eR.findAll(); }

    @Override
    public List<Especie> searchByVeda(boolean veda) { return eR.findByEnVeda(veda); }

    @Override
    public Optional<Especie> listId(Long id) { return eR.findById(id); }

    @Override
    public void delete(Long id) { eR.deleteById(id); }
}



