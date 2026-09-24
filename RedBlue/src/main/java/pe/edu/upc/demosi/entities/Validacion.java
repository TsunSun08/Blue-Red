package pe.edu.upc.demosi.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "validaciones")
public class Validacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValidacion;
    @ManyToOne
    @JoinColumn(name = "idLoteCaptura", nullable = false)
    private LoteCaptura loteCaptura;
    @Column(name = "especieDetectada", length = 45)
    private String especieDetectada;
    @Column(name = "cantidadAnalizada")
    private int cantidadAnalizada;
    @Column(name = "longitudMinimaDetectada")
    private float longitudMinimaDetectada;
    @Column(name = "porcentajeConfianza")
    private float porcentajeConfianza;
    @Column(name = "porcentajeCumplimiento")
    private float porcentajeCumplimiento;
    @Column(name = "objetoReferenciaDetectado", length = 45)
    private String objetoReferenciaDetectado;
    @Column(name = "resultado", length = 45)
    private String resultado;
    @Column(name = "fechaValidacion")
    private LocalDate fechaValidacion;

    public Validacion() {
    }

    public Long getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(Long idValidacion) {
        this.idValidacion = idValidacion;
    }

    public LoteCaptura getLoteCaptura() {
        return loteCaptura;
    }

    public void setLoteCaptura(LoteCaptura loteCaptura) {
        this.loteCaptura = loteCaptura;
    }

    public String getEspecieDetectada() {
        return especieDetectada;
    }

    public void setEspecieDetectada(String especieDetectada) {
        this.especieDetectada = especieDetectada;
    }

    public int getCantidadAnalizada() {
        return cantidadAnalizada;
    }

    public void setCantidadAnalizada(int cantidadAnalizada) {
        this.cantidadAnalizada = cantidadAnalizada;
    }

    public float getLongitudMinimaDetectada() {
        return longitudMinimaDetectada;
    }

    public void setLongitudMinimaDetectada(float longitudMinimaDetectada) {
        this.longitudMinimaDetectada = longitudMinimaDetectada;
    }

    public float getPorcentajeConfianza() {
        return porcentajeConfianza;
    }

    public void setPorcentajeConfianza(float porcentajeConfianza) {
        this.porcentajeConfianza = porcentajeConfianza;
    }

    public float getPorcentajeCumplimiento() {
        return porcentajeCumplimiento;
    }

    public void setPorcentajeCumplimiento(float porcentajeCumplimiento) {
        this.porcentajeCumplimiento = porcentajeCumplimiento;
    }

    public String getObjetoReferenciaDetectado() {
        return objetoReferenciaDetectado;
    }

    public void setObjetoReferenciaDetectado(String objetoReferenciaDetectado) {
        this.objetoReferenciaDetectado = objetoReferenciaDetectado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDate getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(LocalDate fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }
}
