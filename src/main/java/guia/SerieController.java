package guia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/pesquisa", method = RequestMethod.POST)
	public void postSerie(@RequestBody Serie serie) {
		if (!serieRepository.exists(serie.getId())) {
			serieRepository.save(serie);			
		}

		
	}
	
	@RequestMapping(value= "/pesquisa", method = RequestMethod.GET)
	public List<Serie> getSerie() {
		return serieRepository.findAll();
		
		
	}

}
