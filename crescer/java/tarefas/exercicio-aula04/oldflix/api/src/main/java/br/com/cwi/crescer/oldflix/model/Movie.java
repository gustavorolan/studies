package br.com.cwi.crescer.oldflix.model;
import  lombok.*;

import javax.persistence.*;
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
    private String description;
    private boolean isItAvailable;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String rentResponsible;
    private LocalDate rentDate;
    private String imageUrl;
}
