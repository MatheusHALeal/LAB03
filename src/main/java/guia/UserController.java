package guia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

	private UserRepository userRepository;
	

	@Autowired
	private UserService userService;

	@Autowired
	public UserController(UserRepository usuarioRepository) {
		this.userRepository = usuarioRepository;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> cadastrar(@RequestBody User usuario) {
		User usuarioCadastrado = userService.cadastrar(usuario);
		if (usuario == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(usuarioCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> logar(@RequestBody User usuario) {
		usuario = userService.logar(usuario);
		if (usuario == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}


}
