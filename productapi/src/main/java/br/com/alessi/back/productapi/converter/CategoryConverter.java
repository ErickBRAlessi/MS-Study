package br.com.alessi.back.productapi.converter;

import br.com.alessi.back.productapi.dto.CategoryDTO;
import br.com.alessi.back.productapi.model.Category;

public abstract class CategoryConverter {

    public static CategoryDTO convert(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .nome(category.getNome())
                .build();
    }

    public static Category convert(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .nome(categoryDTO.getNome())
                .build();
    }

}
