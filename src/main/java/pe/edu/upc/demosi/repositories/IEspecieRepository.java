package pe.edu.upc.demosi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi.entities.Especie;

import java.util.List;

@Repository
public interface IEspecieRepository extends JpaRepository<Especie, Long> {
    public List<Especie> findByEnVeda(boolean enVeda);
}
