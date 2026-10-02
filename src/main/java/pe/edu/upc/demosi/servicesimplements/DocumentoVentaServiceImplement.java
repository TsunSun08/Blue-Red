package pe.edu.upc.demosi.servicesimplements;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import pe.edu.upc.demosi.dtos.DetalleDocumentoVentaDTOInsert;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTO;
import pe.edu.upc.demosi.dtos.DocumentoVentaDTOList;
import pe.edu.upc.demosi.dtos.DocumentoVentaRegistroDTOInsert;

import pe.edu.upc.demosi.entities.DetalleVenta;
import pe.edu.upc.demosi.entities.DocumentoVenta;
import pe.edu.upc.demosi.entities.LoteCaptura;
import pe.edu.upc.demosi.entities.Usuario;

import pe.edu.upc.demosi.exceptions.ResourceNotFoundException;

import pe.edu.upc.demosi.repositories.IDetalleVentaRepository;
import pe.edu.upc.demosi.repositories.IDocumentoVentaRepository;
import pe.edu.upc.demosi.repositories.ILoteCapturaRepository;
import pe.edu.upc.demosi.repositories.IUsuarioRepository;

import pe.edu.upc.demosi.servicesinterfaces.IDocumentoVentaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    // HU11 - Registrar documento de venta
    @Override
    @Transactional
    public DocumentoVenta registrar(DocumentoVentaRegistroDTOInsert dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));

        if (usuario.getRol() == null ||
                !"Restaurante".equalsIgnoreCase(usuario.getRol().getNombreRol())) {

            throw new IllegalArgumentException(
                    "El usuario debe tener rol Restaurante"
            );
        }

        for (DetalleDocumentoVentaDTOInsert detalleDTO : dto.getDetalles()) {

            LoteCaptura lote = loteCapturaRepository
                    .findById(detalleDTO.getIdLoteCaptura())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Lote de captura no encontrado"
                            )
                    );

            Float pesoCompradoAnterior =
                    detalleVentaRepository.obtenerPesoCompradoPorLote(
                            lote.getIdLoteCaptura()
                    );

            float pesoDisponible =
                    lote.getPesoTotal() - pesoCompradoAnterior;

            if (detalleDTO.getPesoComprado() > pesoDisponible) {

                throw new IllegalArgumentException(
                        "El peso comprado supera el peso disponible del lote"
                );
            }
        }

        DocumentoVenta documentoVenta = new DocumentoVenta();

        documentoVenta.setUsuario(usuario);
        documentoVenta.setTipoDocumento(dto.getTipoDocumento());
        documentoVenta.setFechaEmision(LocalDate.now());

        float montoTotal = 0;

        for (DetalleDocumentoVentaDTOInsert detalleDTO : dto.getDetalles()) {
            montoTotal = montoTotal + detalleDTO.getSubtotal();
        }

        documentoVenta.setMontoTotal(montoTotal);

        DocumentoVenta documentoGuardado =
                documentoVentaRepository.save(documentoVenta);

        for (DetalleDocumentoVentaDTOInsert detalleDTO : dto.getDetalles()) {

            LoteCaptura lote = loteCapturaRepository
                    .findById(detalleDTO.getIdLoteCaptura())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Lote de captura no encontrado"
                            )
                    );

            DetalleVenta detalleVenta = new DetalleVenta();

            detalleVenta.setDocumentoVenta(documentoGuardado);
            detalleVenta.setLoteCaptura(lote);
            detalleVenta.setPesoComprado(detalleDTO.getPesoComprado());
            detalleVenta.setSubtotal(detalleDTO.getSubtotal());

            detalleVentaRepository.save(detalleVenta);
        }

        return documentoGuardado;
    }

    // HU12 - Listar documentos de venta
    @Override
    public List<DocumentoVentaDTOList> listar() {

        List<DocumentoVenta> documentos =
                documentoVentaRepository.findAll();

        return documentos.stream().map(documento -> {

            DocumentoVentaDTOList dto =
                    new DocumentoVentaDTOList();

            dto.setIdDocumentoVenta(
                    documento.getIdDocumentoVenta()
            );

            dto.setIdUsuario(
                    documento.getUsuario().getIdUsuario()
            );

            dto.setFechaEmision(
                    documento.getFechaEmision()
            );

            dto.setMontoTotal(
                    documento.getMontoTotal()
            );

            dto.setTipoDocumento(
                    documento.getTipoDocumento()
            );

            return dto;

        }).toList();
    }

    // HU13 - Buscar documento de venta por ID
    @Override
    public DocumentoVentaDTO buscarPorId(Long id) {

        DocumentoVenta documentoVenta =
                documentoVentaRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Documento de venta no encontrado"
                                )
                        );

        DocumentoVentaDTO dto =
                new DocumentoVentaDTO();

        dto.setIdDocumentoVenta(
                documentoVenta.getIdDocumentoVenta()
        );

        dto.setIdUsuario(
                documentoVenta.getUsuario().getIdUsuario()
        );

        dto.setFechaEmision(
                documentoVenta.getFechaEmision()
        );

        dto.setMontoTotal(
                documentoVenta.getMontoTotal()
        );

        dto.setTipoDocumento(
                documentoVenta.getTipoDocumento()
        );

        return dto;
    }

    // HU45 - Consultar costo total de compras por especie
    @Override
    public List<CostoCompraEspecieDTO> obtenerCostoComprasPorEspecie(
            Long idRestaurante,
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        Usuario restaurante =
                usuarioRepository.findById(idRestaurante)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Restaurante no encontrado"
                                )
                        );

        if (restaurante.getRol() == null ||
                !"Restaurante".equalsIgnoreCase(
                        restaurante.getRol().getNombreRol()
                )) {

            throw new IllegalArgumentException(
                    "El usuario debe tener rol Restaurante"
            );
        }

        if (fechaInicio.isAfter(fechaFin)) {

            throw new IllegalArgumentException(
                    "La fecha de inicio no puede ser posterior a la fecha de fin"
            );
        }

        return detalleVentaRepository.obtenerCostoComprasPorEspecie(
                idRestaurante,
                fechaInicio,
                fechaFin
        );
    }

    // Query 46
    @Override
    public Double costoTotalGeneralPorUsuario(Long idUsuario, LocalDate fechaInicio, LocalDate fechaFin) {

        Usuario restaurante = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante no encontrado."));

        if (restaurante.getRol() == null ||
                !"ROLE_RESTAURANTE".equalsIgnoreCase(restaurante.getRol().getNombreRol())) {
            throw new IllegalArgumentException("El usuario debe tener rol Restaurante.");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        return documentoVentaRepository.costoTotalGeneralPorUsuarioRestaurante(
                idUsuario,
                fechaInicio,
                fechaFin
        );
    }

    // HU14 - Buscar documento de venta por ID
    @Override
    public Optional<DocumentoVenta> listId(Long id) {
        return documentoVentaRepository.findById(id);
    }

    // HU14 - Actualizar documento de venta
    @Override
    public void update(DocumentoVenta documentoVenta) {
        documentoVentaRepository.save(documentoVenta);
    }

    // HU15 - Eliminar documento de venta
    @Override
    public void delete(Long id) {
        documentoVentaRepository.deleteById(id);
    }
}