package br.com.alessi.back.productapi.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
public class CategoryDTO {

    @NotNull
    private Long id;
    private String nome;

}
