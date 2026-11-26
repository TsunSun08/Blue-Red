package pe.edu.upc.demosi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.demosi.dtos.ErrorResponse;
import pe.edu.upc.demosi.dtos.ReconocimientoEspecieDTO;
import pe.edu.upc.demosi.servicesinterfaces.IReconocimientoEspecieService;

@RestController
@RequestMapping("/api/reconocimiento-especie")
@PreAuthorize("hasRole('ADMIN')")
public class ReconocimientoEspecieController {

    private final IReconocimientoEspecieService rS;

    public ReconocimientoEspecieController(IReconocimientoEspecieService rS) {
        this.rS = rS;
    }

    //reconocer la especie de un pez a partir de una fotografia
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReconocimientoEspecieDTO> reconocer(
            @RequestParam("imagen") MultipartFile imagen) {

        return ResponseEntity.ok(rS.reconocer(imagen));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleServicioNoDisponible(
            IllegalStateException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "El servicio de reconocimiento no está disponible en este momento.",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleErrorRoboflow(
            RestClientException ex,
            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_GATEWAY.value(),
                "No se pudo analizar la fotografía con el servicio de reconocimiento.",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(error);
    }
}
