package pe.edu.upc.demosi.servicesinterfaces;
import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOList;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface IDocumentoVentaService {

    // Query 46
    Double costoTotalGeneralPorUsuario(
            Long idUsuario,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

    Optional<DocumentoVenta> listId(Long id);
    void update(DocumentoVenta documentoVenta);
    void delete(Long id);

    DocumentoVenta registrar(DocumentoVentaDTOInsert dto);
    List<DocumentoVentaDTOList> listar();
    DocumentoVentaDTO buscarPorId(Long id);
    List<CostoCompraEspecieDTO> obtenerCostoComprasPorEspecie(
            Long idRestaurante,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );
}