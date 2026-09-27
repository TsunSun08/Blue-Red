package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.entities.DocumentoVenta;

import java.time.LocalDate;
import java.util.Optional;

public interface IDocumentoVentaService {
    public Double costoTotalGeneralPorUsuario(Long idUsuario, LocalDate fechaInicio, LocalDate fechaFin);
    Optional<DocumentoVenta> listId(Long id);
    void update(DocumentoVenta documentoVenta);
}
