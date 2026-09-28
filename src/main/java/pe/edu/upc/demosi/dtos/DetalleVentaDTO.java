package pe.edu.upc.demosi.dtos;

public class DetalleVentaDTO {

    private Long idDetalleVenta;
    private Long idDocumentoVenta;
    private Long idLoteCaptura;
    private float pesoComprado;
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
