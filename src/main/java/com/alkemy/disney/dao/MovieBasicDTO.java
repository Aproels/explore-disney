package com.alkemy.disney.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieBasicDTO {
    private Long id;

    private String title;

    private String creationDate;
    private String imagen;
}
