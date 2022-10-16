package br.com.cwi.crescer.oldflix.model;
import  lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String desc;
    private boolean isItAvailable;
    private Category category;
    private String rentResponsible;
    private LocalDate rentDate;
    private String imageUrl;
}
