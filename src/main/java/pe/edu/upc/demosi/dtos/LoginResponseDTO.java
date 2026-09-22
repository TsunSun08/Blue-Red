package pe.edu.upc.demosi.dtos;

public class LoginResponseDTO {
    private String token;
    private String usuarioNombre;

    public LoginResponseDTO(String token, String usuarioNombre) {
        this.token = token;
        this.usuarioNombre = usuarioNombre;
    }

    public String getToken() {
        return token;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }
}
