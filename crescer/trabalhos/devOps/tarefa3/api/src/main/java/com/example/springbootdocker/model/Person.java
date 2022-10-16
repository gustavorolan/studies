package com.example.springbootdocker.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "personId")
@EqualsAndHashCode(of = "personId")
@Setter
@Getter
@Builder
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(nullable = false,unique=true)
    private String name;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private Integer votesCounter;

    @OneToMany(mappedBy = "person")
    private List<Vote> voteList;
}
