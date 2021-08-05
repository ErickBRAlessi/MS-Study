package br.com.alessi.back.userapi.converter;

import br.com.alessi.back.common.dto.UserDTO;
import br.com.alessi.back.userapi.model.User;

import java.util.Date;

public abstract class UserConverter{

    public static User convert(UserDTO userDTO) {
        return User.builder()
                .nome(userDTO.getNome())
                .key(userDTO.getKey())
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
                .key(user.getKey())
                .endereco(user.getEndereco())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .telefone(user.getTelefone())
                .dataCadastro(user.getDataCadastro())
                .build();
    }
}
