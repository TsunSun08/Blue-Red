package pe.edu.upc.demosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi.entities.LoteCaptura;

import java.util.List;

@Repository
public interface ILoteCapturaRepository extends JpaRepository<LoteCaptura, Long> {

    @Query(value = "select lc.id_lote_captura, e.nombre_comun, lc.fecha_captura, lc.cantidad_peces,\n" +
            "\t\tlc.peso_total, lc.precio_lote, lc.estado\n" +
            "from lotes_captura lc\n" +
            "inner join usuarios u on u.id_usuario = lc.id_usuario\n" +
            "inner join especies e on e.id_especie = lc.id_especie\n" +
            "where u.id_usuario = ?1\n", nativeQuery = true)
    public List<Object[]> hisorialPorUsuarioPescador(Long idUsuario);

    @Query(value = "SELECT e.nombre_comun, lc.cantidad_peces, lc.fecha_captura, lc.latitud, lc.longitud, u.nombre, lc.estado " +
            "from lotes_captura lc " +
            "inner join especies e on lc.id_especie = e.id_especie " +
            "inner join usuarios u on lc.id_usuario = u.id_usuario " +
            "where LOWER(e.nombre_comun) like LOWER(CONCAT('%', :nombreEspecie, '%'))",
            nativeQuery = true)
    List<Object[]> buscarLotesPorNombreEspecie(@Param("nombreEspecie") String nombreEspecie);

    //HU37 - Consultar datos de pesca
    @Query("""
        SELECT lc
        FROM LoteCaptura lc
        WHERE (:estado IS NULL OR :estado = ''
               OR LOWER(lc.estado) LIKE LOWER(CONCAT('%', :estado, '%')))
        """)
    List<LoteCaptura> consultarDatosPesca(@Param("estado") String estado);

    public boolean existsByEspecie_IdEspecie(Long idEspecie);
}