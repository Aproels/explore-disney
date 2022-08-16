package com.alkemy.disney.repository.specifications;

import com.alkemy.disney.dao.MovieFiltersDTO;
import com.alkemy.disney.dao.PersonageFiltersDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );

            }
            if(StringUtils.hasLength(filtersDTO.getCreationDate())){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getCreationDate(),formatter);


                predicates.add(

                        criteriaBuilder.equal(root.<LocalDate>get("fechaCreacion"),date)

                );


            }
            if(StringUtils.hasLength((filtersDTO.getGender()))){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("gender")),
                                "%" + filtersDTO.getGender().toLowerCase() + "%"
                        )
                );

            }



            //remover duplicados
            query.distinct(true);

            //ordenar
            String orderByField= "fechaCreacion";
            query.orderBy(
                    filtersDTO.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };






    }
}
