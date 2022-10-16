package br.com.cwi.crescer.api.model;

import br.com.cwi.crescer.api.security.model.UserAccount;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doubt {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime dateTimeCreation;

    @Column(nullable = false)
    private Boolean finished;

    @Column(nullable = false)
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "attachment_id")
    private Attachment image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doubt")
    private List<Comment> comments;
}
