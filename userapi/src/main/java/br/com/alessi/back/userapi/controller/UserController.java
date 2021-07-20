package br.com.alessi.back.userapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alessi.back.userapi.dto.UserDTO;

@RestController
public class UserController {
	
	public static List<UserDTO> usuarios = new ArrayList<UserDTO>();
	
	@PostConstruct
	public void initializeList() {
		UserDTO userDTO = new UserDTO("Nome 1","09529184980","Rua Do Caqui, 1","email@email.com","(41)2315-4545", new Date());
		usuarios.add(userDTO);
	}

	@GetMapping("/")
	public String getMensagem() {
		return "Springboot Hello World";
	}

	@GetMapping("/user")
	public List<UserDTO> getUsuarios(){
		return usuarios;
	}

	
}
