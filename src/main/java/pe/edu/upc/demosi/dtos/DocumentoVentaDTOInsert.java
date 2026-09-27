package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class DocumentoVentaDTOInsert {

    private Long idDocumentoVenta;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;

    @NotNull(message = "La fecha de emisión es obligatoria.")
    private LocalDate fechaEmision;

    @Positive(message = "El monto total debe ser positivo.")
    private float montoTotal;

    private String tipoDocumento;

    public Long getIdDocumentoVenta() {
        return idDocumentoVenta;
    }

    public void setIdDocumentoVenta(Long idDocumentoVenta) {
        this.idDocumentoVenta = idDocumentoVenta;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
