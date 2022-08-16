package com.alkemy.disney.repository.specifications;


import com.alkemy.disney.dto.PersonageFiltersDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;


@Component
public class PersonageSpecification {
    public Specification<EntityPersonage> getByFilters(PersonageFiltersDTO filtersDTO) {
        return(root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength((filtersDTO.getName()))){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );

            }
            if(StringUtils.hasLength(valueOf(filtersDTO.getAge()))){
                predicates.add(

                        criteriaBuilder.equal(root.get("edad"),filtersDTO.getAge())

                        );


            }

            if(!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<EntityMovie, EntityPersonage> join= root.join("movies", JoinType.INNER);
               //TODO: movieSeriesID puede estar mal
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));

            }

            //remover duplicados
            query.distinct(true);

            //ordenar
            String orderByField= "nombre";
            query.orderBy(
                    filtersDTO.isASC()?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };






    }
}
