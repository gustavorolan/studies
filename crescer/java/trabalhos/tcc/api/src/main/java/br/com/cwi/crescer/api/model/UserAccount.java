package br.com.cwi.crescer.api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "userId")
@EqualsAndHashCode(of = "userId")
@Setter
@Getter
@Builder
@Entity
public class UserAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String nickname;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String  password;

    @Column
    private String profileImg;

    @JsonIgnore
    @ManyToMany(mappedBy = "userAccountList",cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "userAccount")
    @JsonIgnore
    private List<LikePost> likePostList;

    @OneToMany(mappedBy = "userAccount")
    @JsonIgnore
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.EAGER )
    @JoinColumn(name = "userIdPermission")
    private List<Permission> permissionList;

    @OneToMany(mappedBy = "userAccount")
    private List<Notification> notificationList;

}
