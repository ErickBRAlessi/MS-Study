package br.com.alessi.back.productapi.service;

import br.com.alessi.back.common.dto.ShopDTO;
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
    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        return shop.map(ShopConverter::convert).orElse(null);
    }

    public ShopDTO save(ShopDTO shopDTO) {
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));
        Shop shop = ShopConverter.convert(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return ShopConverter.convert(shop);
    }
}


