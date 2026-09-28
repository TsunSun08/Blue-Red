package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ValidacionDTOInsert {

    private Long idValidacion;

    @NotNull(message = "El ID del lote de captura es obligatorio.")
    private Long idLoteCaptura;

    @NotBlank(message = "La especie detectada es obligatoria.")
    private String especieDetectada;

    @NotNull(message = "La cantidad analizada es obligatoria.")
    private int cantidadAnalizada;

    @NotNull(message = "La longitud mínima detectada es obligatoria.")
    private float longitudMinimaDetectada;

    @NotNull(message = "El porcentaje de confianza es obligatorio.")
    private float porcentajeConfianza;

    @NotNull(message = "El porcentaje de cumplimiento es obligatorio.")
    private float porcentajeCumplimiento;

    @NotBlank(message = "El objeto de referencia detectado es obligatorio.")
    private String objetoReferenciaDetectado;

    @NotBlank(message = "El resultado es obligatorio.")
    private String resultado;

    @NotNull(message = "La fecha de validación es obligatoria.")
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