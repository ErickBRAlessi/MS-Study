package br.com.alessi.back.productapi.service;

import br.com.alessi.back.common.dto.ProductDTO;
import br.com.alessi.back.common.exception.CategoryNotFoundException;
import br.com.alessi.back.common.exception.ProductNotFoundException;
import br.com.alessi.back.productapi.converter.ProductConverter;
import br.com.alessi.back.productapi.model.Product;
import br.com.alessi.back.productapi.repository.CategoryRepository;
import br.com.alessi.back.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(ProductConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        return productRepository.getProductByCategory(categoryId)
                .stream()
                .map(ProductConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return ProductConverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    /*
    o existsById verifica
    se um determinado Id existe no banco de dados,
    retornando apenas true ou false
     */
    public ProductDTO save(ProductDTO productDTO) {
        Boolean existsCategory = categoryRepository
                .existsById(productDTO.getCategory().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product product = productRepository
                .save(ProductConverter.convert(productDTO));
        return ProductConverter.convert(product);
    }

    public void delete(long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return;
        }
        throw new ProductNotFoundException();
    }

}
