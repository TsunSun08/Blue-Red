package pe.edu.upc.demosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi.entities.Validacion;

@Repository
public interface IValidacionRepository extends JpaRepository<Validacion, Long> {
}