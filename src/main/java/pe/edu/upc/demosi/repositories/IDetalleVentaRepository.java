package pe.edu.upc.demosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi.entities.DetalleVenta;


@Repository
public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    @Query("SELECT COALESCE(SUM(d.pesoComprado), 0) " +
            "FROM DetalleVenta d " +
            "WHERE d.loteCaptura.idLoteCaptura = :idLoteCaptura")
    Float obtenerPesoCompradoPorLote(@Param("idLoteCaptura") Long idLoteCaptura);
}