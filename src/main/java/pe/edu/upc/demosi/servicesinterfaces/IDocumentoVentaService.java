package pe.edu.upc.demosi.servicesinterfaces;

import java.time.LocalDate;

public interface IDocumentoVentaService {
    public Double costoTotalGeneralPorUsuario(Long idUsuario, LocalDate fechaInicio, LocalDate fechaFin);
}
