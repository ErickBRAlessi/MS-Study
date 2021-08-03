package br.com.alessi.back.userapi.controller;

import br.com.alessi.back.common.dto.UserDTO;
import br.com.alessi.back.userapi.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;


/*
	GET: os métodos GET devem recuperar dados, não afetando o estado da aplicação. Podem receber parâmetros, mas estes devem ser utilizados apenas para a recuperação de dados, nunca para uma atualização ou inserção.
	POST: os métodos POST enviam dados para o servidor para serem processados. Os dados vão no corpo da requisição e não na URL. Normalmente são utilizados para criar novos recursos no servidor.
	PATCH: funciona de modo simular ao POST, mas deve ser utilizado para atualizar as informações no servidor e não para novos registros.
	DELETE: utilizado para excluir elementos do servidor.
*/

@Log
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize() {
        log.log(Level.FINE, "user controller post construct");
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users/cpf/{cpf}")
    public UserDTO findByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

    @DeleteMapping("/users/{id}")
    public UserDTO delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PostMapping("/users")
    public UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping("/users/search")
    public List<UserDTO> queryByName(
            @RequestParam(name = "nome") String nome) {
        return userService.queryByName(nome);
    }

}
