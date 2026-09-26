package pe.edu.upc.demosi.dtos;

import java.time.LocalDate;

public class ValidacionDTO {
    private Long idValidacion;
    private Long idLoteCaptura;
    private String especieDetectada;
    private int cantidadAnalizada;
    private float longitudMinimaDetectada;
    private float porcentajeConfianza;
    private float porcentajeCumplimiento;
    private String objetoReferenciaDetectado;
    private String resultado;
    private LocalDate fechaValidacion;

    public Long getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(Long idValidacion) {
        this.idValidacion = idValidacion;
    }

    public Long getIdLoteCaptura() {
        return idLoteCaptura;
    }

    public void setIdLoteCaptura(Long idLoteCaptura) {
        this.idLoteCaptura = idLoteCaptura;
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
