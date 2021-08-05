package br.com.alessi.back.productapi.controller;

import br.com.alessi.back.common.dto.ReportDTO;
import br.com.alessi.back.common.dto.ShopDTO;
import br.com.alessi.back.productapi.service.ReportService;
import br.com.alessi.back.productapi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
            @RequestParam(name = "valorMinimo", required = false)
                    Float valorMinimo) {
        return reportService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ReportDTO getReportByDate(
            @RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
        return reportService.getReportByDate(dataInicio, dataFim);
    }


    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(
            @PathVariable String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    public ResponseEntity<ShopDTO> newShop(@RequestHeader(name = "key", required = true) String key,
                                           @Valid @RequestBody ShopDTO shopDTO) {
        return ResponseEntity.ok().body(shopService.save(shopDTO, key));
        //  return shopService.save(shopDTO, key);
    }
}
