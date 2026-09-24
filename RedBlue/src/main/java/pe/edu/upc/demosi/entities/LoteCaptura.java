package pe.edu.upc.demosi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotes_captura")
public class LoteCaptura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLoteCaptura;
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idEspecie", nullable = false)
    private Especie especie;
    @Column(name = "fechaCaptura", nullable = false)
    private LocalDate fechaCaptura;
    @Column(name = "latitud")
    private float latitud;
    @Column(name = "longitud")
    private float longitud;
    @Column(name = "cantidadPeces", nullable = false)
    private int cantidadPeces;
    @Column(name = "pesoTotal", nullable = false)
    private float pesoTotal;
    @Column(name = "imagenReferencia", length = 255)
    private String imagenReferencia;
    @Column(name = "precioLote")
    private float precioLote;
    @Column(name = "estado", length = 45)
    private String estado;

    public LoteCaptura() {
    }

    public Long getIdLoteCaptura() {
        return idLoteCaptura;
    }

    public void setIdLoteCaptura(Long idLoteCaptura) {
        this.idLoteCaptura = idLoteCaptura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
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
