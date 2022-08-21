package com.alkemy.disney.dto;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieFiltersDTO {




    private String title;




    private  Long genderId;

    private String order;

    public MovieFiltersDTO(String title, Long genderId, String order) {

        this.title = title;
        this.genderId = genderId;
        this.order = order;

    }

    public boolean isASC() {

        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;

    }
}