package br.com.alessi.back.productapi.repository;

import br.com.alessi.back.common.dto.ReportDTO;
import br.com.alessi.back.productapi.model.Shop;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Component
public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext //esse objeto quem faz a conexão com o banco de dados
    private EntityManager entityManager;


    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
        String sql = getSqlShopByFilter(dataFim, valorMinimo);
        Query query = getQueryShopByFilter(dataInicio, dataFim, valorMinimo, sql);
        return query.getResultList();
    }

    @Override
    public ReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id), sum(sp.total), avg(sp.total)");
        sb.append("from shopping.shop sp ");
        sb.append("where sp.date >= :dataInicio ");
        sb.append("and sp.date <= :dataFim ");

        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        Object[] result = (Object[]) query.getSingleResult();
        ReportDTO reportDTO = new ReportDTO();
        //BigDecimal para count , e Double para consultas com as funções sum e avg
        reportDTO.setCount(((BigInteger) result[0]).intValue());
        reportDTO.setTotal((Double) result[1]);
        reportDTO.setMean((Double) result[2]);

        return reportDTO;
    }

    private Query getQueryShopByFilter(Date dataInicio, Date dataFim, Float valorMinimo, String sql) {
        Query query = entityManager.createQuery(sql);
        query.setParameter("dataInicio", dataInicio);
        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }
        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }
        return query;
    }

    private String getSqlShopByFilter(Date dataFim, Float valorMinimo) {
        StringBuilder sb = new StringBuilder();

        sb.append("select s ");
        sb.append("from shop s ");
        sb.append("where s.date >= :dataInicio ");
        if (dataFim != null) {
            sb.append("and s.date <= :dataFim ");
        }
        if (valorMinimo != null) {
            sb.append("and s.total >= :valorMinimo ");
        }
        return sb.toString();
    }
}
