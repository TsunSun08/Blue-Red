package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.repositories.IDocumentoVentaRepository;
import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;

import java.time.LocalDate;

@Service
public class DocumentoVentaServiceImplement implements IDocumentoVentaService {
    private  final IDocumentoVentaRepository docvR;

    public DocumentoVentaServiceImplement(IDocumentoVentaRepository docvR) {
        this.docvR = docvR;
    }

    @Override
    public Double costoTotalGeneralPorUsuario(Long idUsuario, LocalDate fechaInicio, LocalDate fechaFin) {
        return docvR.costoTotalGeneralPorUsuarioRestaurante(idUsuario, fechaInicio, fechaFin);
    }
}
