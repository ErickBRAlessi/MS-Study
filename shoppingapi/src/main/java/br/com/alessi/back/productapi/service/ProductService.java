package br.com.alessi.back.productapi.service;

import br.com.alessi.back.common.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Value("${project.url.product-api}")
    private String productApiURL;

    public ProductDTO getProductByIdentifier(String productIdentifier) {
        RestTemplate restTemplate = new RestTemplate();
        String url =
                new StringBuilder().append(productApiURL).append("/").append(productIdentifier).toString();
        try {
            ResponseEntity<ProductDTO> response =
                    restTemplate.getForEntity(url, ProductDTO.class);
            return response.getBody();
        } catch (RestClientException e) {
            return null;
        }
    }
}
