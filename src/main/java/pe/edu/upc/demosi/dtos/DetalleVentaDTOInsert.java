package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DetalleVentaDTOInsert {

    @NotNull(message = "El ID del lote de captura es obligatorio.")
    private Long idLoteCaptura;
    @Positive(message = "El peso comprado debe ser mayor a cero.")
    private float pesoComprado;
    @Positive(message = "El subtotal debe ser mayor a cero.")
    private float subtotal;
    public DetalleVentaDTOInsert() {
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