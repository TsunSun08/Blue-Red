package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotBlank;

public class RolDTO {
    private Long idRol;
    @NotBlank(message = "El nombre del rol es obligatorio.")
    private String nombreRol;
    private String descripcion;

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
