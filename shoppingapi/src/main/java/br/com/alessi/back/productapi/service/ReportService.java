package br.com.alessi.back.productapi.service;

import br.com.alessi.back.common.dto.ReportDTO;
import br.com.alessi.back.common.dto.ShopDTO;
import br.com.alessi.back.productapi.converter.ShopConverter;
import br.com.alessi.back.productapi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Transactional
    public List<ShopDTO> getShopsByFilter(Date dataInicio, Date dataFim, Float valorMinimo) {
        return reportRepository
                .getShopByFilters(dataInicio, dataFim, valorMinimo)
                .stream()
                .map(ShopConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim) {
        return reportRepository
                .getReportByDate(dataInicio, dataFim);
    }
}
