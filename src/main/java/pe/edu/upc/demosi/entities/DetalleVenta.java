package pe.edu.upc.demosi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;
    @ManyToOne
    @JoinColumn(name = "idDocumentoVenta", nullable = false)
    private DocumentoVenta documentoVenta;
    @ManyToOne
    @JoinColumn(name = "idLoteCaptura", nullable = false)
    private LoteCaptura loteCaptura;
    @Column(name = "pesoComprado", nullable = false)
    private float pesoComprado;
    @Column(name = "subtotal", nullable = false)
    private float subtotal;

    public DetalleVenta() {
    }

    public Long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public DocumentoVenta getDocumentoVenta() {
        return documentoVenta;
    }

    public void setDocumentoVenta(DocumentoVenta documentoVenta) {
        this.documentoVenta = documentoVenta;
    }

    public LoteCaptura getLoteCaptura() {
        return loteCaptura;
    }

    public void setLoteCaptura(LoteCaptura loteCaptura) {
        this.loteCaptura = loteCaptura;
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
