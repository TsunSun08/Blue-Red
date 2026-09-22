package pe.edu.upc.demosi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "documentos_venta")
public class DocumentoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumentoVenta;
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario; // El comprador (Restaurante)
    @Column(name = "fechaEmision", nullable = false)
    private LocalDate fechaEmision;
    @Column(name = "montoTotal", nullable = false)
    private float montoTotal;
    @Column(name = "tipoDocumento", length = 45)
    private String tipoDocumento;

    public DocumentoVenta() {
    }

    public Long getIdDocumentoVenta() {
        return idDocumentoVenta;
    }

    public void setIdDocumentoVenta(Long idDocumentoVenta) {
        this.idDocumentoVenta = idDocumentoVenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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