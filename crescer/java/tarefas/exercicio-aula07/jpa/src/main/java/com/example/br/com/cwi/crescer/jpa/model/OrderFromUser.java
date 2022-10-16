package com.example.br.com.cwi.crescer.jpa.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@ToString(of = "orderId")
@EqualsAndHashCode(of = "orderId")
@Getter
@Setter
@Entity
public class OrderFromUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String responsibleName;

    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private Situation situation;

    @OneToMany(mappedBy = "user")
    private List<Item> itemList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="credit_card_id")
    private CreditCard creditCard;
}
