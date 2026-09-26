package pe.edu.upc.demosi.dtos;

public class LoginResponseDTO {
    private final String token;
    private final String correo;


    public LoginResponseDTO(String token, String correo) {
        this.token = token;
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public String getCorreo() {
        return correo;
    }
}

