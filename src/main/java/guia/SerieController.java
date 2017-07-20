package guia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="/")
public class SerieController {

	private SerieRepository serieRepository;


	@Autowired
	public SerieController(SerieRepository serieRepository) {
		this.serieRepository = serieRepository;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void postSerie(@RequestBody Serie serie) {
		if (userHaveSerie(serie)) {
			return;			
		} else {
			serieRepository.save(serie);
		}
	}
	
	public boolean userHaveSerie(Serie serie) {
		List<Serie> series = serieRepository.findAll();
		for (Serie serie2 : series) {
			if (serie2.equals(serie)) {
				return true;
			}
		} return false;
	}
	
	
	
	

	@RequestMapping(value = "/getSeries/{idUsuario}", method = RequestMethod.GET)
	public List<Serie> getSeries(@PathVariable Long idUsuario) {
		return serieRepository.findByidUser(idUsuario);
	} 
	
}
