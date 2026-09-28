package pe.edu.upc.demosi.dtos;

public class DocumentacionPescadorDTO {

    private Long idPescador;
    private String nombre;
    private String apellido;
    private String dni;
    private String licenciaPescador;
    private String licenciaEmbarcacion;
    private String certificadoMatricula;
    private String protocoloHabilitacionSanitaria;

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

    public String getLicenciaPescador() {
        return licenciaPescador;
    }

    public void setLicenciaPescador(String licenciaPescador) {
        this.licenciaPescador = licenciaPescador;
    }

    public String getLicenciaEmbarcacion() {
        return licenciaEmbarcacion;
    }

    public void setLicenciaEmbarcacion(String licenciaEmbarcacion) {
        this.licenciaEmbarcacion = licenciaEmbarcacion;
    }

    public String getCertificadoMatricula() {
        return certificadoMatricula;
    }

    public void setCertificadoMatricula(String certificadoMatricula) {
        this.certificadoMatricula = certificadoMatricula;
    }

    public String getProtocoloHabilitacionSanitaria() {
        return protocoloHabilitacionSanitaria;
    }

    public void setProtocoloHabilitacionSanitaria(String protocoloHabilitacionSanitaria) {
        this.protocoloHabilitacionSanitaria = protocoloHabilitacionSanitaria;
    }
}