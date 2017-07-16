package guia;

import java.util.List;
import guia.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long>{
	
	List<Serie> findByTitle(String titulo);
	
	List<Serie> findAll();
	
}
