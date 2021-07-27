package br.com.alessi.back.productapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userIdentifier;
    private float total;
    private Date date;

    @ElementCollection(fetch = FetchType.LAZY) //mapeia o relacionamento de uma coleção dependente
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    // definir  qual é a tabela onde os itens estarão armazenados, no caso, a tabela shop_item . A anotação @JoinColumn define qual coluna da tabela shop_item será unida (join) à tabela shop , no caso a coluna shop_id .
    private List<Item> items;

}
