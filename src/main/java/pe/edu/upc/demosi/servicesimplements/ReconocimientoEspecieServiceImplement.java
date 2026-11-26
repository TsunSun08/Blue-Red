package pe.edu.upc.demosi.servicesimplements;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.demosi.dtos.ReconocimientoEspecieDTO;
import pe.edu.upc.demosi.exceptions.BadRequestException;
import pe.edu.upc.demosi.servicesinterfaces.IReconocimientoEspecieService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ReconocimientoEspecieServiceImplement implements IReconocimientoEspecieService {

    private static final String URL_MODELO = "https://serverless.roboflow.com/fish_2-qum3k/1";
    private static final long TAMANO_MAXIMO = 10L * 1024 * 1024;
    private static final Set<String> TIPOS_PERMITIDOS = Set.of("image/jpeg", "image/png");

    // La clave se lee de la variable de entorno ROBOFLOW_API_KEY (nunca del codigo)
    @Value("${ROBOFLOW_API_KEY:}")
    private String apiKey;

    private final RestClient restClient;

    public ReconocimientoEspecieServiceImplement() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(30000);
        this.restClient = RestClient.builder().requestFactory(factory).build();
    }

    @Override
    public ReconocimientoEspecieDTO reconocer(MultipartFile imagen) {

        if (imagen == null || imagen.isEmpty()) {
            throw new BadRequestException("Debe enviar una fotografía.");
        }
        if (imagen.getSize() > TAMANO_MAXIMO) {
            throw new BadRequestException("La fotografía no debe superar los 10 MB.");
        }
        if (imagen.getContentType() == null
                || !TIPOS_PERMITIDOS.contains(imagen.getContentType().toLowerCase())) {
            throw new BadRequestException("Formato no compatible. Use una imagen JPG o PNG.");
        }
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("La clave de Roboflow no está configurada.");
        }

        String imagenBase64;
        try {
            imagenBase64 = Base64.getEncoder().encodeToString(imagen.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("No se pudo leer la fotografía.");
        }

        // Contrato de Roboflow: POST con la imagen en Base64 como cuerpo y clave Bearer
        Map<?, ?> respuesta = restClient.post()
                .uri(URL_MODELO)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(imagenBase64)
                .retrieve()
                .body(Map.class);

        return interpretar(respuesta);
    }

    // Lectura defensiva de la respuesta de clasificacion (pendiente de validar con una respuesta real)
    private ReconocimientoEspecieDTO interpretar(Map<?, ?> respuesta) {

        if (respuesta == null) {
            throw new IllegalStateException("Roboflow devolvió una respuesta vacía.");
        }

        Object top = respuesta.get("top");
        Object confianza = respuesta.get("confidence");
        if (top instanceof String especie && confianza instanceof Number numero) {
            return new ReconocimientoEspecieDTO(especie, numero.doubleValue());
        }

        Object predicciones = respuesta.get("predictions");
        if (predicciones instanceof List<?> lista) {
            String mejorClase = null;
            double mejorConfianza = -1;
            for (Object item : lista) {
                if (item instanceof Map<?, ?> prediccion
                        && prediccion.get("class") instanceof String clase
                        && prediccion.get("confidence") instanceof Number valor
                        && valor.doubleValue() > mejorConfianza) {
                    mejorClase = clase;
                    mejorConfianza = valor.doubleValue();
                }
            }
            if (mejorClase != null) {
                return new ReconocimientoEspecieDTO(mejorClase, mejorConfianza);
            }
        }

        throw new IllegalStateException("La respuesta de Roboflow no tiene el formato esperado.");
    }
}
