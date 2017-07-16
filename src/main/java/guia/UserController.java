package guia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository usuarioRepository) {
		this.userRepository = usuarioRepository;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void postUser(@RequestBody User user) throws Exception {
		if (userRepository.findByName(user.getName()).isEmpty()) {
			userRepository.save(user);
		} else {
			throw new Exception("err");
		}
	}
	


}
