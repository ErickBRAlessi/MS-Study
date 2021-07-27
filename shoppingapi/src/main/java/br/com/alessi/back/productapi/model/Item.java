package br.com.alessi.back.productapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
//uma classe com a anotação @Embeddable não tem vida própria, ela sempre depende de uma entidade, isto é, de uma classe que tenha a anotação @Entity .
public class Item {

    private String productIdentifier;
    private Float price;

}

