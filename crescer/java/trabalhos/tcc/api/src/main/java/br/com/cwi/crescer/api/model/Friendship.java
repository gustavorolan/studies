package br.com.cwi.crescer.api.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "friendshipId")
@EqualsAndHashCode(of = "friendshipId")
@Setter
@Getter
@Builder
@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "userId")
    private UserAccount userAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "userId")
    private UserAccount userFriendShip;


    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Relation relation;


}
