package pe.edu.upc.demosi.servicesinterfaces;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.demosi.dtos.ReconocimientoEspecieDTO;

public interface IReconocimientoEspecieService {
    public ReconocimientoEspecieDTO reconocer(MultipartFile imagen);
}
