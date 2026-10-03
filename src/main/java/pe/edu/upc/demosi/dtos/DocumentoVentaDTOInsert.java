package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class DocumentoVentaDTOInsert {

    private Long idDocumentoVenta;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;

    private LocalDate fechaEmision;

    private float montoTotal;

    @NotBlank(message = "El tipo de documento es obligatorio.")
    private String tipoDocumento;

    @NotEmpty(message = "Debe ingresar al menos un detalle de venta.")
    private List<DetalleVentaDTOInsert> detalles;

    public DocumentoVentaDTOInsert() {
    }
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
    public List<DetalleVentaDTOInsert> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<DetalleVentaDTOInsert> detalles) {
        this.detalles = detalles;
    }
}