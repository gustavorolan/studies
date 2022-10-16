package br.com.cwi.crescer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "notificationId")
@EqualsAndHashCode(of = "notificationId")
@Setter
@Getter
@Builder
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId")
    private UserAccount userAccount;

    @Column(nullable = false)
    private String notification;
}
