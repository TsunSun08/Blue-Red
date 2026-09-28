package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.LoteCapturaDTO;
import pe.edu.upc.demosi.dtos.LoteCapturaDTOUpdate;
import pe.edu.upc.demosi.entities.LoteCaptura;
import java.util.List;
import java.util.Optional;

public interface ILoteCapturaService {

    public void insert(LoteCaptura loteCaptura);
    public List<LoteCaptura> list();
    public Optional<LoteCaptura> listId(Long id);

    List<Object[]> listarLoteCapturaDeUnUsuario(Long idUsuario);
    List<Object[]> buscarLotesPorNombreEspecie(String nombreEspecie);

    // HU18 - Buscar datos de pesca por ID
    LoteCapturaDTO buscarPorId(Long id);

    // HU19 - Actualizar lote de captura por ID
    LoteCapturaDTO actualizar(Long id, LoteCapturaDTOUpdate dto);

    // HU20 - Eliminar lote de captura
    void eliminar(Long id);

    // HU37 - Consultar datos de pesca
    List<LoteCaptura> consultarDatosPesca(String estado);
}