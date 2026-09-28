package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DetalleVentaDTOInsert {

    private Long idDetalleVenta;

    @NotNull(message = "El ID del documento de venta es obligatorio.")
    private Long idDocumentoVenta;

    @NotNull(message = "El ID del lote de captura es obligatorio.")
    private Long idLoteCaptura;

    @Positive(message = "El peso comprado debe ser positivo.")
    private float pesoComprado;

    @Positive(message = "El subtotal debe ser positivo.")
    private float subtotal;

    public Long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Long getIdDocumentoVenta() {
        return idDocumentoVenta;
    }

    public void setIdDocumentoVenta(Long idDocumentoVenta) {
        this.idDocumentoVenta = idDocumentoVenta;
    }

    public Long getIdLoteCaptura() {
        return idLoteCaptura;
    }

    public void setIdLoteCaptura(Long idLoteCaptura) {
        this.idLoteCaptura = idLoteCaptura;
    }

    public float getPesoComprado() {
        return pesoComprado;
    }

    public void setPesoComprado(float pesoComprado) {
        this.pesoComprado = pesoComprado;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
