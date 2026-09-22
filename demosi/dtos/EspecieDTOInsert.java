package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EspecieDTOInsert {
    private Long idEspecie;
    @NotBlank(message = "El nombre común es obligatorio.")
    private String nombreComun;
    @NotBlank(message = "El nombre científico es obligatorio.")
    private String nombreCientifico;
    @Positive(message = "La talla mínima debe ser mayor a cero.")
    private float tallaMinima;
    @NotNull(message = "Debe indicar si está en veda.")
    private boolean enVeda;

    public Long getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Long idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public float getTallaMinima() {
        return tallaMinima;
    }

    public void setTallaMinima(float tallaMinima) {
        this.tallaMinima = tallaMinima;
    }

    public boolean isEnVeda() {
        return enVeda;
    }

    public void setEnVeda(boolean enVeda) {
        this.enVeda = enVeda;
    }
}
