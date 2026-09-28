package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOList;
import java.util.List;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;


public interface IDocumentoVentaService {

    DocumentoVenta registrar(DocumentoVentaDTOInsert dto);

    List<DocumentoVentaDTOList> listar();

    DocumentoVentaDTO buscarPorId(Long id);
}