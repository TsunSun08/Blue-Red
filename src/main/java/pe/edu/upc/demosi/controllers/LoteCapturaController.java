package pe.edu.upc.demosi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demosi.dtos.LoteCapturaDTO;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;
import pe.edu.upc.demosi.dtos.LoteCapturaDTOUpdate;

@RestController
@RequestMapping("/api/lotes-captura")
public class LoteCapturaController {

    private final ILoteCapturaService loteCapturaService;
    public LoteCapturaController(ILoteCapturaService loteCapturaService) {
        this.loteCapturaService = loteCapturaService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<LoteCapturaDTO> buscarPorId(@PathVariable Long id) {

        LoteCapturaDTO dto = loteCapturaService.buscarPorId(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LoteCapturaDTO> actualizar(
            @PathVariable Long id,
            @RequestBody LoteCapturaDTOUpdate dto) {

        LoteCapturaDTO loteActualizado = loteCapturaService.actualizar(id, dto);

        return new ResponseEntity<>(loteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        loteCapturaService.eliminar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}