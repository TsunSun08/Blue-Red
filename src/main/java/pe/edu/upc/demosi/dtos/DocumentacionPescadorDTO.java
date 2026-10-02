package pe.edu.upc.demosi.dtos;

public class DocumentacionPescadorDTO {

    private Long idPescador;
    private String nombre;
    private String apellido;
    private String dni;
    private String numeroLicencia;

    public DocumentacionPescadorDTO() {
    }

    public Long getIdPescador() {
        return idPescador;
    }

    public void setIdPescador(Long idPescador) {
        this.idPescador = idPescador;
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
}