package br.com.cwi.crescer.api.model;

import br.com.cwi.crescer.api.security.model.UserAccount;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Builder
public class Video {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String endPoint;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Boolean active;

    @Lob
    @Column
    private byte[] supportMaterial;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserAccount author;

    @Enumerated(STRING)
    @Column(nullable = false)
    private VideoType type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "video")
    private List<Evaluation> evaluations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;
}
