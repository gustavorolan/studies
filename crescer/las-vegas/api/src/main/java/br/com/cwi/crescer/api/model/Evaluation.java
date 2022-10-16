package br.com.cwi.crescer.api.model;

import br.com.cwi.crescer.api.security.model.UserAccount;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Builder
public class Evaluation {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime dateTimeCreation;

    private LocalDateTime dateTimeUpdate;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name="author_id")
    private UserAccount userAccount;
}