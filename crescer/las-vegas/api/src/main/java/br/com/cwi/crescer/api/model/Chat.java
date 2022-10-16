package br.com.cwi.crescer.api.model;

import br.com.cwi.crescer.api.security.model.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userOneId")
    UserAccount userOne;

    @ManyToOne
    @JoinColumn(name = "userTwoId")
    UserAccount userTwo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<Message> messageList;

    private LocalDateTime lastMessage;

    private boolean read;
}
