package br.com.cwi.crescer.api.security.model;

import br.com.cwi.crescer.api.model.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String presentation;

    private String registration;

    private String identifier;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(STRING)
    private Permission permission;

    private String relevantLinks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private List<Permissions> permissoes;

    @OneToOne
    @JoinColumn(name = "attachment_id")
    private Attachment profileImage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Comment> createdComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Course> createdCourses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UserCourse",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private List<Course> myCourses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Video> createdVideos;

    @OneToOne
    @JoinColumn(name = "created_team_id")
    private Team createdTeam;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Doubt> doubts;

    private Integer assessment;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount")
    private List<Evaluation> evaluations;

}
