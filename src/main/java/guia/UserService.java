package guia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User cadastrar(User user) {
		if (this.search(user.getName()) == null) {
			return userRepository.save(user);
		} else {
			return null;
		}
	}
	

	public User search(String user) {
		List<User> users = userRepository.findAll();
		for (User us : users) {
			if (us.getName().equals(user)) {
				return us;
			}
		}
		return null;
		
	}
	
	public User logar(User usuario) {
		return logar(usuario.getName(), usuario.getPassword());
	}
	
	public User logar(String email, String senha) {
		List<User> usuarios = userRepository.findAll();
		for (User usuario : usuarios) {
			if (usuario.getName().equals(email)) {
				if(usuario.getPassword().equals(senha)) {
					return usuario;
				}
			}
		}
		return null;
	}
}