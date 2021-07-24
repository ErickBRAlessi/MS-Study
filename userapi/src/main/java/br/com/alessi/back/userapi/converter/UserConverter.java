package br.com.alessi.back.userapi.converter;

import br.com.alessi.back.userapi.dto.UserDTO;
import br.com.alessi.back.userapi.model.User;

import java.util.Date;

public abstract class UserConverter{

    public static User convert(UserDTO userDTO) {
        return User.builder()
                .nome(userDTO.getNome())
                .endereco(userDTO.getEndereco())
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .telefone(userDTO.getTelefone())
                .dataCadastro(userDTO.getDataCadastro() == null ? new Date() : userDTO.getDataCadastro())
                .build();
    }

    public static UserDTO convert(User user) {
        return UserDTO.builder()
                .nome(user.getNome())
                .endereco(user.getEndereco())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .telefone(user.getTelefone())
                .dataCadastro(user.getDataCadastro())
                .build();
    }
}
