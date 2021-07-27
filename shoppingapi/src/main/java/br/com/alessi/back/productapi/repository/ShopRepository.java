package br.com.alessi.back.productapi.repository;

import br.com.alessi.back.productapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/*Note os padrões importantes para essas buscas. O primeiro é o findAll ,
indicando que a busca será por um ou mais resultados, o segundo é o
By{Atributo} , que indica por qual atributo será feita a busca, e o terceiro é
o GreaterThan , que faz um filtro de apenas valores maiores do que o
passado como parâmetro serão buscados.
 */


@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAllByUserIdentifier(
            String userIdentifier);

    List<Shop> findAllByTotalGreaterThan(Float total);

    List<Shop> findAllByDateAfter(Date date);
}

