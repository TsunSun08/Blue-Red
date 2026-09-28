package pe.edu.upc.demosi.dtos;
public class CostoCompraEspecieDTO {

    private String nombreEspecie;
    private Double costoTotal;

    public CostoCompraEspecieDTO() {
    }
    public CostoCompraEspecieDTO(String nombreEspecie, Double costoTotal) {
        this.nombreEspecie = nombreEspecie;
        this.costoTotal = costoTotal;
    }
    public String getNombreEspecie() {
        return nombreEspecie;
    }
    public void setNombreEspecie(String nombreEspecie) {
        this.nombreEspecie = nombreEspecie;
    }
    public Double getCostoTotal() {
        return costoTotal;
    }
    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }
}