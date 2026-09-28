package pe.edu.upc.demosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi.entities.DetalleVenta;
import pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    @Query("SELECT COALESCE(SUM(d.pesoComprado), 0) " +
            "FROM DetalleVenta d " +
            "WHERE d.loteCaptura.idLoteCaptura = :idLoteCaptura")
    Float obtenerPesoCompradoPorLote(@Param("idLoteCaptura") Long idLoteCaptura);
    boolean existsByLoteCaptura_IdLoteCaptura(Long idLoteCaptura);

    @Query("SELECT new pe.edu.upc.demosi.dtos.CostoCompraEspecieDTO(" +
            "e.nombreComun, SUM(CAST(d.subtotal AS double))) " +
            "FROM DetalleVenta d " +
            "JOIN d.documentoVenta dv " +
            "JOIN d.loteCaptura lc " +
            "JOIN lc.especie e " +
            "WHERE dv.usuario.idUsuario = :idRestaurante " +
            "AND dv.fechaEmision BETWEEN :fechaInicio AND :fechaFin " +
            "GROUP BY e.idEspecie, e.nombreComun")
    List<CostoCompraEspecieDTO> obtenerCostoComprasPorEspecie(
            @Param("idRestaurante") Long idRestaurante,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}

