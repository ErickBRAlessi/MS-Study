package br.com.alessi.back.productapi.repository;

import br.com.alessi.back.common.dto.ReportDTO;
import br.com.alessi.back.productapi.model.Shop;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository {

    List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

    ReportDTO getReportByDate(Date dataInicio, Date dataFim);


}
