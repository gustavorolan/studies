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
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private LocalDateTime dateTimeUpdate;

    @Column(nullable = false)
    private LocalDateTime dateTimeCreation;

    @Column(nullable = false)
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "attachment_id")
    private Attachment image;

    @Column(nullable = false)
    private Boolean approvementStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private UserAccount author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Video> videos;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    private List<Team> teams;

    private Integer assessment;
}
