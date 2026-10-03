package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
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

    // HU11 - Registrar documento de venta
    DocumentoVenta registrar(DocumentoVentaDTOInsert dto);

    // HU12 - Listar documentos de venta
    List<DocumentoVenta> listar();

    // HU13 - Buscar documento de venta por ID
    DocumentoVenta buscarPorId(Long id);

    // HU45 - Consultar costo total de compras por especie
    List<CostoCompraEspecieDTO> obtenerCostoComprasPorEspecie(
            Long idRestaurante,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );
}