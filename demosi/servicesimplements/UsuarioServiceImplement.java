package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;
import pe.edu.upc.demosi.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    private final IUsuarioRepository uR;
    public UsuarioServiceImplement(IUsuarioRepository uR){
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
}
