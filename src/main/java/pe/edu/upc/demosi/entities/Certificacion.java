package pe.edu.upc.demosi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "certificaciones")
public class Certificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertificacion;
    @ManyToOne
    @JoinColumn(name = "idValidacion", nullable = false)
    private Validacion validacion;
    @Column(name = "codigoCertificado", length = 45, unique = true)
    private String codigoCertificado;
    @Column(name = "fechaEmision")
    private LocalDate fechaEmision;
    @Column(name = "estadoCertificado", length = 45)
    private String estadoCertificado;

    public Certificacion() {
    }

    public Long getIdCertificacion() {
        return idCertificacion;
    }

    public void setIdCertificacion(Long idCertificacion) {
        this.idCertificacion = idCertificacion;
    }

    public Validacion getValidacion() {
        return validacion;
    }

    public void setValidacion(Validacion validacion) {
        this.validacion = validacion;
    }

    public String getCodigoCertificado() {
        return codigoCertificado;
    }

    public void setCodigoCertificado(String codigoCertificado) {
        this.codigoCertificado = codigoCertificado;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstadoCertificado() {
        return estadoCertificado;
    }

    public void setEstadoCertificado(String estadoCertificado) {
        this.estadoCertificado = estadoCertificado;
    }
}