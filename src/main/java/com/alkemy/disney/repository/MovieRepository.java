package com.alkemy.disney.repository;

import com.alkemy.disney.entitys.EntityMovie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface MovieRepository extends JpaRepository<EntityMovie, Long>, JpaSpecificationExecutor<EntityMovie> {

List<EntityMovie> findAll(Specification<EntityMovie> spec);

}
