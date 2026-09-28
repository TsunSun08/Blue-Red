package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.LoteCapturaDTO;
import pe.edu.upc.demosi.dtos.LoteCapturaDTOUpdate;

public interface ILoteCapturaService {

    LoteCapturaDTO buscarPorId(Long id);

    LoteCapturaDTO actualizar(Long id, LoteCapturaDTOUpdate dto);
    void eliminar(Long id);
}