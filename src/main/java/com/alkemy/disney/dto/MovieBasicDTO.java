package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class MovieBasicDTO {


    private String title;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String creationDate;
    private String image;

}
