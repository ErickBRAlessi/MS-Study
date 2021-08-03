package br.com.alessi.back.productapi.converter;

import br.com.alessi.back.common.dto.ItemDTO;
import br.com.alessi.back.productapi.model.Item;

public abstract class ItemConverter {

    public static Item convert(ItemDTO itemDTO) {
        return Item.builder()
                .price(itemDTO.getPrice())
                .productIdentifier(itemDTO.getProductIdentifier())
                .build();
    }

    public static ItemDTO convert(Item item) {
        return ItemDTO.builder()
                .price(item.getPrice())
                .productIdentifier(item.getProductIdentifier())
                .build();
    }

}
