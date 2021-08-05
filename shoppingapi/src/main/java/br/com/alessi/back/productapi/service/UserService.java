package br.com.alessi.back.productapi.service;

import br.com.alessi.back.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Value("${project.url.user-api}")
    private String userApiURL;

    public UserDTO getUserByCpf(String cpf, String key) {
        RestTemplate restTemplate = new RestTemplate();
        String url = userApiURL + "/cpf/" + cpf;
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(url);
        builder.queryParam("key", key);

        try {
            ResponseEntity<UserDTO> response =
                    restTemplate.getForEntity(builder.toUriString(), UserDTO.class);
            return response.getBody();
        } catch (RestClientException e) {
            return null;
        }
    }

}
