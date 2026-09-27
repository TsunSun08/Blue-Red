package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demosi.repositories.IDetalleVentaRepository;
import pe.edu.upc.demosi.repositories.IDocumentoVentaRepository;
import pe.edu.upc.demosi.repositories.ILoteCapturaRepository;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;
import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOInsert;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.entities.Usuario;
import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;

import pe.edu.upc.demosi.dtos.DetalleVentaDTOInsert;
import pe.edu.upc.demosi.entities.DetalleVenta;
import pe.edu.upc.demosi.entities.LoteCaptura;
import java.time.LocalDate;

@Service
public class DocumentoVentaServiceImplement implements IDocumentoVentaService {

    private final IDocumentoVentaRepository documentoVentaRepository;
    private final IDetalleVentaRepository detalleVentaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ILoteCapturaRepository loteCapturaRepository;

    public DocumentoVentaServiceImplement(
            IDocumentoVentaRepository documentoVentaRepository,
            IDetalleVentaRepository detalleVentaRepository,
            IUsuarioRepository usuarioRepository,
            ILoteCapturaRepository loteCapturaRepository) {

        this.documentoVentaRepository = documentoVentaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.usuarioRepository = usuarioRepository;
        this.loteCapturaRepository = loteCapturaRepository;
    }
    @Override
    @Transactional
    public DocumentoVenta registrar(DocumentoVentaDTOInsert dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if (usuario.getRol() == null ||
                !"Restaurante".equalsIgnoreCase(usuario.getRol().getNombreRol())) {
            throw new IllegalArgumentException("El usuario debe tener rol Restaurante");
        }

        for (DetalleVentaDTOInsert detalleDTO : dto.getDetalles()) {

            LoteCaptura lote = loteCapturaRepository.findById(detalleDTO.getIdLoteCaptura())
                    .orElseThrow(() -> new ResourceNotFoundException("Lote de captura no encontrado"));
            Float pesoCompradoAnterior =
                    detalleVentaRepository.obtenerPesoCompradoPorLote(lote.getIdLoteCaptura());
            float pesoDisponible = lote.getPesoTotal() - pesoCompradoAnterior;
            if (detalleDTO.getPesoComprado() > pesoDisponible) {
                throw new IllegalArgumentException(
                        "El peso comprado supera el peso disponible del lote");
            }
        }

        DocumentoVenta documentoVenta = new DocumentoVenta();
        documentoVenta.setUsuario(usuario);
        documentoVenta.setTipoDocumento(dto.getTipoDocumento());
        documentoVenta.setFechaEmision(LocalDate.now());
        float montoTotal = 0;

        for (DetalleVentaDTOInsert detalleDTO : dto.getDetalles()) {
            montoTotal = montoTotal + detalleDTO.getSubtotal();
        }
        documentoVenta.setMontoTotal(montoTotal);
        DocumentoVenta documentoGuardado =
                documentoVentaRepository.save(documentoVenta);

        for (DetalleVentaDTOInsert detalleDTO : dto.getDetalles()) {

            LoteCaptura lote = loteCapturaRepository.findById(detalleDTO.getIdLoteCaptura())
                    .orElseThrow(() -> new ResourceNotFoundException("Lote de captura no encontrado"));

            DetalleVenta detalleVenta = new DetalleVenta();

            detalleVenta.setDocumentoVenta(documentoGuardado);
            detalleVenta.setLoteCaptura(lote);
            detalleVenta.setPesoComprado(detalleDTO.getPesoComprado());
            detalleVenta.setSubtotal(detalleDTO.getSubtotal());

            detalleVentaRepository.save(detalleVenta);
        }



        return documentoGuardado;

    }
}