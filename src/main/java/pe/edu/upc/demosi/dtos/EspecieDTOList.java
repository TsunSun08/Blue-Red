package pe.edu.upc.demosi.dtos;

public class EspecieDTOList {
    private Long idEspecie;
    private String nombreComun;
    private String nombreCientifico;
    private float tallaMinima;
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

    public boolean isEnVeda() {
        return enVeda;
    }

    public void setEnVeda(boolean enVeda) {
        this.enVeda = enVeda;
    }

    public float getTallaMinima() {
        return tallaMinima;
    }

    public void setTallaMinima(float tallaMinima) {
        this.tallaMinima = tallaMinima;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }
}
