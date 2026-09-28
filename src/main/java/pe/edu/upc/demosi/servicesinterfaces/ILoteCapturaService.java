package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.LoteCaptura;

import java.util.List;
import java.util.Optional;

public interface ILoteCapturaService {

    public void insert(LoteCaptura loteCaptura);

    public List<LoteCaptura> list();

    public Optional<LoteCaptura> listId(Long id);

    List<Object[]> listarLoteCapturaDeUnUsuario(Long idUsuario);

    List<Object[]> buscarLotesPorNombreEspecie(String nombreEspecie);

    List<LoteCaptura> consultarDatosPesca(String estado);
}