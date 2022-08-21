package com.alkemy.disney.repository.specifications;


import com.alkemy.disney.dto.PersonageFiltersDTO;
import com.alkemy.disney.entitys.EntityMovie;
import com.alkemy.disney.entitys.EntityPersonage;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );

            }
            if(!ObjectUtils.isEmpty(filtersDTO.getAge())){
                Long  age = filtersDTO.getAge();

                predicates.add(

                        criteriaBuilder.equal(root.get("age"),age)

                );


            }
            if(!ObjectUtils.isEmpty(filtersDTO.getWeight())){
                Double weight = filtersDTO.getWeight();

                predicates.add(

                        criteriaBuilder.equal(root.get("weight"),weight)

                );


            }


            if(!ObjectUtils.isEmpty(filtersDTO.getMoviesId())){
                Join<EntityMovie, EntityPersonage> join= root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMoviesId()));

            }

            //remover duplicados
            query.distinct(true);



            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };






    }
}
