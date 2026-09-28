package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.dtos.LoteCapturaDTO;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demosi.repositories.ILoteCapturaRepository;
import pe.edu.upc.demosi.servicesinterfaces.ILoteCapturaService;

import pe.edu.upc.demosi.dtos.LoteCapturaDTOUpdate;
import pe.edu.upc.demosi.entities.Especie;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.repositories.IEspecieRepository;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;

@Service
public class LoteCapturaServiceImplement implements ILoteCapturaService {

    private final ILoteCapturaRepository loteCapturaRepository;

    private final IUsuarioRepository usuarioRepository;
    private final IEspecieRepository especieRepository;


    public LoteCapturaServiceImplement(ILoteCapturaRepository loteCapturaRepository,
                                       IUsuarioRepository usuarioRepository,
                                       IEspecieRepository especieRepository) {
        this.loteCapturaRepository = loteCapturaRepository;
        this.usuarioRepository = usuarioRepository;
        this.especieRepository = especieRepository;
    }


    @Override
    public LoteCapturaDTO buscarPorId(Long id) {
        LoteCaptura loteCaptura = loteCapturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote de captura no encontrado"));
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

    @Override
    public LoteCapturaDTO actualizar(Long id, LoteCapturaDTOUpdate dto) {

        LoteCaptura loteCaptura = loteCapturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote de captura no encontrado"));

        if (!loteCaptura.getEstado().equalsIgnoreCase("Pendiente")) {
            throw new IllegalArgumentException("El lote debe estar pendiente de validación para ser actualizado");
        }

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Especie especie = especieRepository.findById(dto.getIdEspecie())
                .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada"));

        loteCaptura.setUsuario(usuario);
        loteCaptura.setEspecie(especie);
        loteCaptura.setFechaCaptura(dto.getFechaCaptura());
        loteCaptura.setLatitud(dto.getLatitud());
        loteCaptura.setLongitud(dto.getLongitud());
        loteCaptura.setCantidadPeces(dto.getCantidadPeces());
        loteCaptura.setPesoTotal(dto.getPesoTotal());
        loteCaptura.setImagenReferencia(dto.getImagenReferencia());
        loteCaptura.setPrecioLote(dto.getPrecioLote());

        LoteCaptura loteActualizado = loteCapturaRepository.save(loteCaptura);

        LoteCapturaDTO respuesta = new LoteCapturaDTO();

        respuesta.setIdLoteCaptura(loteActualizado.getIdLoteCaptura());
        respuesta.setIdUsuario(loteActualizado.getUsuario().getIdUsuario());
        respuesta.setIdEspecie(loteActualizado.getEspecie().getIdEspecie());
        respuesta.setFechaCaptura(loteActualizado.getFechaCaptura());
        respuesta.setLatitud(loteActualizado.getLatitud());
        respuesta.setLongitud(loteActualizado.getLongitud());
        respuesta.setCantidadPeces(loteActualizado.getCantidadPeces());
        respuesta.setPesoTotal(loteActualizado.getPesoTotal());
        respuesta.setImagenReferencia(loteActualizado.getImagenReferencia());
        respuesta.setPrecioLote(loteActualizado.getPrecioLote());
        respuesta.setEstado(loteActualizado.getEstado());

        return respuesta;
    }



















}