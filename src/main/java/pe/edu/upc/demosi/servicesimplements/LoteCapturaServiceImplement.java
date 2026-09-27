package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.repositories.ILoteCapturaRepository;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;

import java.util.List;
import java.util.Optional;

@Service
public class LoteCapturaServiceImplement implements ILoteCapturaService {
    private final ILoteCapturaRepository lcR;
    public LoteCapturaServiceImplement(ILoteCapturaRepository lcR) {
        this.lcR = lcR;
    }

    @Override
    public void insert(LoteCaptura loteCaptura) {
        lcR.save(loteCaptura);
    }

    @Override
    public List<LoteCaptura> list() {
        return lcR.findAll();
    }

    @Override
    public Optional<LoteCaptura> listId(Long id) {
        return lcR.findById(id);
    }

    @Override
    public List<Object[]> listarLoteCapturaDeUnUsuario(Long idUsuario) {
        return lcR.hisorialPorUsuarioPescador(idUsuario);
    }


}
