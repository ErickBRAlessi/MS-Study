package br.com.alessi.back.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {

    @NotBlank
    private String userIdentifier;

    private Float total;

    private Date date;

    @NotNull
    private List<ItemDTO> items;

}
