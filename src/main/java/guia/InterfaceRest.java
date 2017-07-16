package guia;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterfaceRest {

	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public String helloWord() {
		return "Oi, eu estou funcionando!";
	}
}
