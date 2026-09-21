package pe.edu.upc.demosi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTOInsert {
    private Long idUsuario;
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;
    @NotBlank(message = "El correo es obligatorio.")
    private String correo;
    @NotBlank(message = "La contraseña es obligatoria.")
    private String contrasena;
    @NotBlank(message = "El teléfono es obligatorio.")
    private String telefono;
    @NotBlank(message = "El DNI es obligatorio.")
    private String dni;
    @NotBlank(message = "El número de licencia es obligatorio.")
    private String numeroLicencia;
    @NotBlank(message = "El Ruc es obligatorio.")
    private String ruc;
    @NotNull(message = "El id del Rol es obligatorio.")
    private Long idRol;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
}
