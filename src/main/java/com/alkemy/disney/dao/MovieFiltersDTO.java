package com.alkemy.disney.dao;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieFiltersDTO {




    private String title;

    private String creationDate;


    private String gender;

    private String order;

    public MovieFiltersDTO(String title, String creationDate, String gender, String order) {

        this.title = title;
        this.creationDate = creationDate;

        this.gender = gender;
        this.order = order;

    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;

    }
}