package pe.edu.upc.demosi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "especies")
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEspecie;
    @Column(name = "nombreComun", length = 45, nullable = false)
    private String nombreComun;
    @Column(name = "nombreCientifico", length = 45, nullable = false)
    private String nombreCientifico;
    @Column(name = "tallaMinima", nullable = false)
    private float tallaMinima;
    @Column(name = "enVeda", nullable = false)
    private boolean enVeda;

    public Especie() {
    }

    public Especie(Long idEspecie, String nombreComun, String nombreCientifico, float tallaMinima, boolean enVeda) {
        this.idEspecie = idEspecie;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.tallaMinima = tallaMinima;
        this.enVeda = enVeda;
    }

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
