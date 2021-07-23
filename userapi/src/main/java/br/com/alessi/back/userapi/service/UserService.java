package br.com.alessi.back.userapi.service;

import br.com.alessi.back.userapi.converter.UserConverter;
import br.com.alessi.back.userapi.dto.UserDTO;
import br.com.alessi.back.userapi.model.User;
import br.com.alessi.back.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(UserConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(UserConverter::convert).orElse(null);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(UserConverter.convert(userDTO));
        return UserConverter.convert(user);
    }

    public UserDTO delete(long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> userRepository.delete(value));
        return null;
    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return UserConverter.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> users = userRepository.queryByNomeLike(name);
        return users
                .stream()
                .map(UserConverter::convert)
                //.map(user -> UserConverter.convert(user)) the same thing as the line above
                .collect(Collectors.toList());
    }
}
