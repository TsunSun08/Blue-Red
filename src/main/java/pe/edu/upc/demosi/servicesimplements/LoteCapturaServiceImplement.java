package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.dtos.LoteCapturaDTO;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.repositories.IDetalleVentaRepository;
import pe.edu.upc.demosi.repositories.IEspecieRepository;
import pe.edu.upc.demosi.repositories.ILoteCapturaRepository;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;
import pe.edu.upc.demosi.repositories.IValidacionRepository;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;

import java.util.List;
import java.util.Optional;

@Service
public class LoteCapturaServiceImplement implements ILoteCapturaService {

    private final ILoteCapturaRepository loteCapturaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IEspecieRepository especieRepository;
    private final IDetalleVentaRepository detalleVentaRepository;
    private final IValidacionRepository validacionRepository;

    public LoteCapturaServiceImplement(
            ILoteCapturaRepository loteCapturaRepository,
            IUsuarioRepository usuarioRepository,
            IEspecieRepository especieRepository,
            IDetalleVentaRepository detalleVentaRepository,
            IValidacionRepository validacionRepository) {

        this.loteCapturaRepository = loteCapturaRepository;
        this.usuarioRepository = usuarioRepository;
        this.especieRepository = especieRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.validacionRepository = validacionRepository;
    }

    @Override
    public void insert(LoteCaptura loteCaptura) {
        loteCapturaRepository.save(loteCaptura);
    }

    @Override
    public List<LoteCaptura> list() {
        return loteCapturaRepository.findAll();
    }

    @Override
    public Optional<LoteCaptura> listId(Long id) {
        return loteCapturaRepository.findById(id);
    }

    // HU18 - Buscar datos de pesca por ID
    @Override
    public LoteCapturaDTO buscarPorId(Long id) {

        LoteCaptura loteCaptura = loteCapturaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lote de captura no encontrado"
                        )
                );

        LoteCapturaDTO dto = new LoteCapturaDTO();

        dto.setIdLoteCaptura(loteCaptura.getIdLoteCaptura());
        dto.setIdUsuario(loteCaptura.getUsuario().getIdUsuario());
        dto.setIdEspecie(loteCaptura.getEspecie().getIdEspecie());
        dto.setFechaCaptura(loteCaptura.getFechaCaptura());
        dto.setLatitud(loteCaptura.getLatitud());
        dto.setLongitud(loteCaptura.getLongitud());
        dto.setCantidadPeces(loteCaptura.getCantidadPeces());
        dto.setPesoTotal(loteCaptura.getPesoTotal());
        dto.setImagenReferencia(loteCaptura.getImagenReferencia());
        dto.setPrecioLote(loteCaptura.getPrecioLote());
        dto.setEstado(loteCaptura.getEstado());

        return dto;
    }

    // HU19 - Actualizar lote de captura por ID
    @Override
    public void actualizar(LoteCaptura loteCaptura) {

        loteCapturaRepository.save(loteCaptura);
    }

    // HU20 - Eliminar lote de captura
    @Override
    public void eliminar(Long id) {

        LoteCaptura loteCaptura =
                loteCapturaRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lote de captura no encontrado"
                                )
                        );

        boolean tieneValidacion =
                validacionRepository
                        .existsByLoteCaptura_IdLoteCaptura(id);
        boolean tieneVenta =
                detalleVentaRepository
                        .existsByLoteCaptura_IdLoteCaptura(id);

        if (tieneValidacion || tieneVenta) {
            throw new IllegalArgumentException(
                    "El lote tiene una validación o venta asociada y no puede ser eliminado"
            );
        }
        loteCapturaRepository.delete(loteCaptura);
    }

    //HU40
    @Override
    public List<Object[]> listarLoteCapturaDeUnUsuario(
            Long idUsuario) {

        Usuario pescador = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Pescador no encontrado"));

        if (pescador.getRol() == null ||
                !"ROLE_PESCADOR".equalsIgnoreCase(pescador.getRol().getNombreRol())) {
            throw new IllegalArgumentException("El usuario debe tener rol Pescador");
        }

        return loteCapturaRepository
                .hisorialPorUsuarioPescador(idUsuario);
    }

    //HU44
    @Override
    public List<Object[]> buscarLotesPorNombreEspecie(
            String nombreEspecie) {

        return loteCapturaRepository
                .buscarLotesPorNombreEspecie(nombreEspecie);
    }

    //HU37 - Consultar datos de pesca
    @Override
    public List<LoteCaptura> consultarDatosPesca(String estado) {

        return loteCapturaRepository
                .consultarDatosPesca(estado);
    }

    @Override
    public boolean verificarAsociacionConEspecie(Long idEspecie) {
        return loteCapturaRepository.existsByEspecie_IdEspecie(idEspecie);
    }
}