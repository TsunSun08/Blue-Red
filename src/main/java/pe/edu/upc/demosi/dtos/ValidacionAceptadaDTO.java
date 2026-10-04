package pe.edu.upc.demosi.dtos;

import java.time.LocalDate;

public class ValidacionAceptadaDTO {

    private String especieDetectada;
    private float porcentajeCumplimiento;
    private LocalDate fechaValidacion;

    public String getEspecieDetectada() {
        return especieDetectada;
    }

    public void setEspecieDetectada(String especieDetectada) {
        this.especieDetectada = especieDetectada;
    }

    public float getPorcentajeCumplimiento() {
        return porcentajeCumplimiento;
    }

    public void setPorcentajeCumplimiento(float porcentajeCumplimiento) {
        this.porcentajeCumplimiento = porcentajeCumplimiento;
    }

    public LocalDate getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(LocalDate fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }
}