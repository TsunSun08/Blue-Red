package pe.edu.upc.demosi.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/documento-venta")
public class DocumentoVentaController {
    private final IDocumentoVentaService docvS;
    private final IUsuarioService uS;
    private final ModelMapper modelMapper;

    public DocumentoVentaController(IDocumentoVentaService docvS, IUsuarioService uS, ModelMapper modelMapper) {
        this.docvS = docvS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }


    //Query 46
    @GetMapping("/costo-total-general/{idUsuario}")
    public ResponseEntity<Double> costoTotalGeneralPorUsuario(@PathVariable Long idUsuario, @RequestParam LocalDate fechaInicio,@RequestParam LocalDate fechaFin){
        Double costoTotal = docvS.costoTotalGeneralPorUsuario(
                idUsuario,
                fechaInicio,
                fechaFin
        );
        return ResponseEntity.ok(costoTotal);
    }
}
