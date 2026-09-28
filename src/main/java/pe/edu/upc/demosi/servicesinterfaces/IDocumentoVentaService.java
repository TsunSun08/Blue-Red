package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import java.time.LocalDate;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOList;
import java.util.List;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;


public interface IDocumentoVentaService {

    DocumentoVenta registrar(DocumentoVentaDTOInsert dto);

    List<DocumentoVentaDTOList> listar();

    DocumentoVentaDTO buscarPorId(Long id);

    List<CostoCompraEspecieDTO> obtenerCostoComprasPorEspecie(
            Long idRestaurante,
            LocalDate fechaInicio,
            LocalDate fechaFin);

}