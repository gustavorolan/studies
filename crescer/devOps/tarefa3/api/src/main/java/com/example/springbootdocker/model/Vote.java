package com.example.springbootdocker.model;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "votesId")
@EqualsAndHashCode(of = "votesId")
@Setter
@Getter
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long votesId;

    @Column(nullable = false,unique=true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="personId")
    private Person person;
}
