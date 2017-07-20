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
	public void postUser(@RequestBody User user) {
		if (userRepository.findByName(user.getName()) == null) {
			userRepository.save(user);
			return;
		} else {

			throw new RuntimeException();
		}
	}
	
	@RequestMapping(value = "/getin", method = RequestMethod.POST)
	public User login(@RequestBody User user) {
		
		if (validation(user.getName(), user.getPassword()) == null) {
			throw new RuntimeException();
		} else {
			return validation(user.getName(), user.getPassword());
		}

	}
	
	public User validation(String name, String password) {
		User user = userRepository.findByName(name);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return user;
			} return null;
		} return user;
	}



}
