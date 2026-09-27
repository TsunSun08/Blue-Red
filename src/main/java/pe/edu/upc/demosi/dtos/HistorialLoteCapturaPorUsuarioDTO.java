package pe.edu.upc.demosi.dtos;

import java.time.LocalDate;

public class HistorialLoteCapturaPorUsuarioDTO {
    private Long idLoteCaptura;
    private String especie;
    private LocalDate fechaCaptura;
    private int cantidadPeces;
    private float pesoTotal;
    private float precioLote;
    private String estado;

    public Long getIdLoteCaptura() {
        return idLoteCaptura;
    }

    public void setIdLoteCaptura(Long idLoteCaptura) {
        this.idLoteCaptura = idLoteCaptura;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public LocalDate getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(LocalDate fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public int getCantidadPeces() {
        return cantidadPeces;
    }

    public void setCantidadPeces(int cantidadPeces) {
        this.cantidadPeces = cantidadPeces;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public float getPrecioLote() {
        return precioLote;
    }

    public void setPrecioLote(float precioLote) {
        this.precioLote = precioLote;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
