package br.com.alessi.back.productapi.converter;

import br.com.alessi.back.productapi.dto.ProductDTO;
import br.com.alessi.back.productapi.model.Product;

public abstract class ProductConverter{

    public static ProductDTO convert(Product product) {
        return ProductDTO.builder()
                .nome(product.getNome())
                .preco(product.getPreco())
                .productIdentifier(product.getProductIdentifier())
                .descricao(product.getDescricao())
                .category(CategoryConverter.convert(product.getCategory()))
                .build();
    }

    public static Product convert(ProductDTO productDTO) {
        return Product.builder()
                .nome(productDTO.getNome())
                .preco(productDTO.getPreco())
                .descricao(productDTO.getDescricao())
                .productIdentifier(productDTO.getProductIdentifier())
                .category(productDTO.getCategory() != null ? CategoryConverter.convert(productDTO.getCategory()) : null)
                .build();
    }

}
