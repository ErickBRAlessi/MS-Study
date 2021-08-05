package br.com.alessi.back.productapi.service;

import br.com.alessi.back.common.dto.ItemDTO;
import br.com.alessi.back.common.dto.ProductDTO;
import br.com.alessi.back.common.dto.ShopDTO;
import br.com.alessi.back.common.exception.ProductNotFoundException;
import br.com.alessi.back.common.exception.UserNotFoundException;
import br.com.alessi.back.productapi.converter.ShopConverter;
import br.com.alessi.back.productapi.model.Shop;
import br.com.alessi.back.productapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public List<ShopDTO> getAll() {
        return shopRepository
                .findAll()
                .stream()
                .map(ShopConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository
                .findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(ShopConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        return shopRepository
                .findAllByDateAfter(shopDTO.getDate())
                .stream()
                .map(ShopConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public ShopDTO findById(long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        return shop.map(ShopConverter::convert).orElse(null);
    }

    @Transactional
    public ShopDTO save(ShopDTO shopDTO, String key) {
        if (userService.getUserByCpf(shopDTO.getUserIdentifier(), key) == null) {
            throw new UserNotFoundException();
        }
        if (!validateProducts(shopDTO.getItems())) {
            throw new ProductNotFoundException();
        }

        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce((float) 0, Float::sum));


        Shop shop = ShopConverter.convert(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return ShopConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService
                    .getProductByIdentifier(
                            item.getProductIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }
}


