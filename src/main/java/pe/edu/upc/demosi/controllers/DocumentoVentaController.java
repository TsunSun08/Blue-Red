package pe.edu.upc.demosi.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOList;
import java.util.List;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;
import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import java.time.LocalDate;

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

    @GetMapping
    public ResponseEntity<List<DocumentoVentaDTOList>> listar() {
        return ResponseEntity.ok(documentoVentaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoVentaDTO> buscarPorId(@PathVariable Long id) {
        DocumentoVentaDTO dto = documentoVentaService.buscarPorId(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/costo-por-especie")
    public ResponseEntity<List<CostoCompraEspecieDTO>> obtenerCostoComprasPorEspecie(
            @RequestParam Long idRestaurante,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {

        List<CostoCompraEspecieDTO> resultado =
                documentoVentaService.obtenerCostoComprasPorEspecie(
                        idRestaurante,
                        fechaInicio,
                        fechaFin);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}