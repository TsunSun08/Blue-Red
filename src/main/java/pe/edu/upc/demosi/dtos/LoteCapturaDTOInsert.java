package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LoteCapturaDTOInsert {
    private Long idLoteCaptura;
    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;
    @NotNull(message = "El ID de la idEspecie es obligatorio.")
    private Long idEspecie;
    @NotNull(message = "La fecha de captura no puede estar vacia.")
    private LocalDate fechaCaptura;
    @NotNull(message = "La latitud es obligatoria.")
    private float latitud;
    @NotNull(message = "La longitud es obligatoria.")
    private float longitud;
    @NotNull(message = "La cantidad de peces es obligatoria.")
    private int cantidadPeces;
    @NotNull(message = "El peso total es obligatorio.")
    private float pesoTotal;
    @NotBlank(message = "La imagen de referencia es obligatoria.")
    private String imagenReferencia;
    @NotNull(message = "El precio del lote es obligatorio.")
    private float precioLote;

    private String estado;

    public Long getIdLoteCaptura() {
        return idLoteCaptura;
    }

    public void setIdLoteCaptura(Long idLoteCaptura) {
        this.idLoteCaptura = idLoteCaptura;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
