package br.com.alessi.back.productapi.converter;

import br.com.alessi.back.productapi.dto.ShopDTO;
import br.com.alessi.back.productapi.model.Shop;

import java.util.stream.Collectors;

public abstract class ShopConverter {

    public static Shop convert(ShopDTO shopDTO) {
        return Shop.builder()
                .userIdentifier(shopDTO.getUserIdentifier())
                .total(shopDTO.getTotal())
                .date(shopDTO.getDate())
                .items(shopDTO
                        .getItems()
                        .stream()
                        .map(ItemConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    public static ShopDTO convert(Shop shop) {
        return ShopDTO.builder()
                .userIdentifier(shop.getUserIdentifier())
                .total(shop.getTotal())
                .date(shop.getDate())
                .items(shop
                        .getItems()
                        .stream()
                        .map(ItemConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }

}


