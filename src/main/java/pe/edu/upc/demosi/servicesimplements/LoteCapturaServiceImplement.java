package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.dtos.LoteCapturaDTO;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.repositories.ILoteCapturaRepository;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;

@Service
public class LoteCapturaServiceImplement implements ILoteCapturaService {

    private final ILoteCapturaRepository loteCapturaRepository;

    public LoteCapturaServiceImplement(ILoteCapturaRepository loteCapturaRepository) {
        this.loteCapturaRepository = loteCapturaRepository;
    }

    @Override
    public LoteCapturaDTO buscarPorId(Long id) {

        LoteCaptura loteCaptura = loteCapturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote de captura no encontrado"));

        LoteCapturaDTO dto = new LoteCapturaDTO();

        dto.setIdLoteCaptura(loteCaptura.getIdLoteCaptura());
        dto.setIdUsuario(loteCaptura.getUsuario().getIdUsuario());
        dto.setIdEspecie(loteCaptura.getEspecie().getIdEspecie());
        dto.setFechaCaptura(loteCaptura.getFechaCaptura());
        dto.setLatitud(loteCaptura.getLatitud());
        dto.setLongitud(loteCaptura.getLongitud());
        dto.setCantidadPeces(loteCaptura.getCantidadPeces());
        dto.setPesoTotal(loteCaptura.getPesoTotal());
        dto.setImagenReferencia(loteCaptura.getImagenReferencia());
        dto.setPrecioLote(loteCaptura.getPrecioLote());
        dto.setEstado(loteCaptura.getEstado());

        return dto;
    }
}