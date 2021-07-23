package br.com.alessi.back.userapi.repository;

import br.com.alessi.back.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpf(String cpf);

    List<User> queryByNomeLike(String name);

}
