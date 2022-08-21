package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entitys.EntityMovie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;
@Component
public class MovieSpecification {
    public Specification<EntityMovie> getByFilters(MovieFiltersDTO filtersDTO) {
        return(root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength((filtersDTO.getTitle()))){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );

            }
            if(!ObjectUtils.isEmpty(filtersDTO.getGenderId())) {
                Long genderId = filtersDTO.getGenderId();
                predicates.add(

                        criteriaBuilder.equal(root.get("genderId"),genderId
                                //"%" + filtersDTO.getGenderId() + "%")

                ));
            }



            //remover duplicados
            query.distinct(true);

            //ordenar
            String orderByField= "creationDate";
            query.orderBy(
                    filtersDTO.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };






    }
}
