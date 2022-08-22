package com.alkemy.disney.dto;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieFiltersDTO {




    private String title;

    private String creationDate;


    private  Long genderId;



    public MovieFiltersDTO(String title, Long genderId,String creationDate) {

        this.title = title;
        this.genderId = genderId;

        this.creationDate=creationDate;
    }

    public boolean isASC() {

        return this.creationDate.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.creationDate.compareToIgnoreCase("DESC") == 0;

    }
}