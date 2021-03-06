package br.com.alessi.back.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String nome;
    private String key;
    private String cpf;
    private String endereco;
    private String email;
	private String telefone;
	private Date dataCadastro;

}
