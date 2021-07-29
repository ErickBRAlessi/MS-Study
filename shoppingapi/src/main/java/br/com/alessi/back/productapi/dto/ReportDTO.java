package br.com.alessi.back.productapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportDTO {

    private Integer count;
    private Double total;
    private Double mean;

}
