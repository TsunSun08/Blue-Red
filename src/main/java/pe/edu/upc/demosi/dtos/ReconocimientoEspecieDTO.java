package pe.edu.upc.demosi.dtos;

public class ReconocimientoEspecieDTO {
    private String especie;
    private double confianza;

    public ReconocimientoEspecieDTO() {
    }

    public ReconocimientoEspecieDTO(String especie, double confianza) {
        this.especie = especie;
        this.confianza = confianza;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getConfianza() {
        return confianza;
    }

    public void setConfianza(double confianza) {
        this.confianza = confianza;
    }
}
