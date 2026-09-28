package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.LoteCapturaDTO;

public interface ILoteCapturaService {

    LoteCapturaDTO buscarPorId(Long id);
}