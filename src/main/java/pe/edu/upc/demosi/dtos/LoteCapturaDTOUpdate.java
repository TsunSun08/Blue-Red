package pe.edu.upc.demosi.dtos;

import java.time.LocalDate;
public class LoteCapturaDTOUpdate {

    private Long idUsuario;
    private Long idEspecie;
    private LocalDate fechaCaptura;
    private float latitud;
    private float longitud;
    private int cantidadPeces;
    private float pesoTotal;
    private String imagenReferencia;
    private float precioLote;

    public LoteCapturaDTOUpdate() {
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Long getIdEspecie() {
        return idEspecie;
    }
    public void setIdEspecie(Long idEspecie) {
        this.idEspecie = idEspecie;
    }
    public LocalDate getFechaCaptura() {
        return fechaCaptura;
    }
    public void setFechaCaptura(LocalDate fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }
    public float getLatitud() {
        return latitud;
    }
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }
    public float getLongitud() {
        return longitud;
    }
    public void setLongitud(float longitud) {
        this.longitud = longitud;
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
    public String getImagenReferencia() {
        return imagenReferencia;
    }
    public void setImagenReferencia(String imagenReferencia) {
        this.imagenReferencia = imagenReferencia;
    }
    public float getPrecioLote() {
        return precioLote;
    }
    public void setPrecioLote(float precioLote) {
        this.precioLote = precioLote;
    }
}