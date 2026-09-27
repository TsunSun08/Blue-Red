package pe.edu.upc.demosi.servicesinterfaces;

import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;

public interface IDocumentoVentaService {

    DocumentoVenta registrar(DocumentoVentaDTOInsert dto);
}