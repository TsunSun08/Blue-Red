package pe.edu.upc.demosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi.entities.DocumentoVenta;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IDocumentoVentaRepository extends JpaRepository<DocumentoVenta, Long> {
    @Query(value = "select coalesce(sum(d.monto_total), 0.0)\n" +
            "from documentos_venta d\n" +
            "where d.id_usuario = ?1 and d.fecha_emision between ?2 and ?3", nativeQuery = true)
    public Double costoTotalGeneralPorUsuarioRestaurante(Long idUsuario, LocalDate fechaInicio, LocalDate fechaFin);
}
