package br.com.alessi.back.productapi.service;

import br.com.alessi.back.productapi.converter.ProductConverter;
import br.com.alessi.back.productapi.dto.ProductDTO;
import br.com.alessi.back.productapi.model.Product;
import br.com.alessi.back.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(ProductConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        return productRepository.getProductByCategory(categoryId)
                .stream()
                .map(ProductConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if(product != null) {
            return ProductConverter.convert(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO){
        return ProductConverter.convert(productRepository.save(ProductConverter.convert(productDTO)));
    }

    public void delete(long productId){
       productRepository.findById(productId).ifPresent(value -> productRepository.delete(value));
    }

}
