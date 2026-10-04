package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.dtos.DocumentacionPescadorDTO;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    private final IUsuarioRepository uR;

    public UsuarioServiceImplement(IUsuarioRepository uR) {
        this.uR = uR;
    }

    @Override
    public void insert(Usuario u) {
        uR.save(u);
    }

    @Override
    public List<Usuario> list() {
        return uR.findAll();
    }

    @Override
    public Optional<Usuario> listId(Long idUsuario) {
        return uR.findById(idUsuario);
    }

    @Override
    public void update(Usuario usuario) {
        uR.save(usuario);
    }

    @Override
    public void delete(Long idUsuario) {
        uR.deleteById(idUsuario);
    }

    // HU38 - Consultar documentación del pescador
    @Override
    public DocumentacionPescadorDTO consultarDocumentacionPescador(Long idPescador) {

        Usuario pescador = uR
                .findByIdUsuarioAndRol_NombreRol(idPescador, "ROLE_PESCADOR")
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un pescador con ID: " + idPescador
                ));

        DocumentacionPescadorDTO dto = new DocumentacionPescadorDTO();

        dto.setIdPescador(pescador.getIdUsuario());
        dto.setNombre(pescador.getNombre());
        dto.setApellido(pescador.getApellido());
        dto.setDni(pescador.getDni());
        dto.setNumeroLicencia(pescador.getNumeroLicencia());

        return dto;
    }
}