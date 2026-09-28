package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import java.time.LocalDate;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOList;
import java.util.List;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;


import java.util.Optional;

public interface IDocumentoVentaService {
    public Double costoTotalGeneralPorUsuario(Long idUsuario, LocalDate fechaInicio, LocalDate fechaFin);
    Optional<DocumentoVenta> listId(Long id);
    void update(DocumentoVenta documentoVenta);
    void delete(Long id);
}

    DocumentoVenta registrar(DocumentoVentaDTOInsert dto);

    List<DocumentoVentaDTOList> listar();

    DocumentoVentaDTO buscarPorId(Long id);

    List<CostoCompraEspecieDTO> obtenerCostoComprasPorEspecie(
            Long idRestaurante,
            LocalDate fechaInicio,
            LocalDate fechaFin);

}