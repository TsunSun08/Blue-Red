package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;

@RestController
@RequestMapping("/api/documentos-venta")
public class DocumentoVentaController {

    private final IDocumentoVentaService documentoVentaService;

    public DocumentoVentaController(IDocumentoVentaService documentoVentaService) {
        this.documentoVentaService = documentoVentaService;
    }

    @PostMapping
    public ResponseEntity<DocumentoVenta> registrar(
            @Valid @RequestBody DocumentoVentaDTOInsert dto) {

        DocumentoVenta documentoVenta = documentoVentaService.registrar(dto);

        return new ResponseEntity<>(documentoVenta, HttpStatus.CREATED);
    }
}