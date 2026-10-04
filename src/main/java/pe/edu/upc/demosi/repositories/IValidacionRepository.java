    package pe.edu.upc.demosi.repositories;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;
    import pe.edu.upc.demosi.entities.Validacion;

    import java.util.List;

    @Repository
    public interface IValidacionRepository extends JpaRepository<Validacion, Long> {

        boolean existsByLoteCaptura_IdLoteCaptura(Long idLoteCaptura);

        //HU42
        @Query(value = "select v.especie_detectada, v.porcentaje_cumplimiento, v.fecha_validacion " +
                "from validaciones v " +
                "inner join lotes_captura lc on lc.id_lote_captura = v.id_lote_captura " +
                "inner join usuarios u on u.id_usuario = lc.id_usuario " +
                "where u.id_usuario = ?1 and v.resultado = 'Rechazado'", nativeQuery = true)
        public List<Object[]> listarValidacionesRechazadasPorPescador(Long idUsuario);

        //HU43
        @Query(value = "select v.especie_detectada, v.porcentaje_cumplimiento, v.fecha_validacion " +
                "from validaciones v " +
                "inner join lotes_captura lc on lc.id_lote_captura = v.id_lote_captura " +
                "inner join usuarios u on u.id_usuario = lc.id_usuario " +
                "where u.id_usuario = ?1 and v.resultado = 'Aceptado'", nativeQuery = true)
        public List<Object[]> listarValidacionesAceptadasPorPescador(Long idUsuario);
    }