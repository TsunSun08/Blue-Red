package pe.edu.upc.demosi.dtos;

import java.util.List;

public class LoginResponseDTO {
    private String token;
    private String correo;
    private List<String> roles;
    private Long idUsuario;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String correo, List<String> roles, Long idUsuario) {
        this.token = token;
        this.correo = correo;
        this.roles = roles;
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}