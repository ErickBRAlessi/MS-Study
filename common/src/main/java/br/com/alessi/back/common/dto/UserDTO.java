package br.com.alessi.back.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private String nome;
    private String cpf;
    private String endereco;
    private String email;
	private String telefone;
	private Date dataCadastro;

}
