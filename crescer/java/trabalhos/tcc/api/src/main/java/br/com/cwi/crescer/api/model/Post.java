package br.com.cwi.crescer.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "postId")
@EqualsAndHashCode(of = "postId")
@Setter
@Getter
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "postUser",
    joinColumns = @JoinColumn(name="postId"),
    inverseJoinColumns = @JoinColumn(name = "userId"))
    private List <UserAccount> userAccountList;

    @OneToMany(mappedBy = "postCommented")
    @JsonIgnore
    private List<Comment> commentList;

    @OneToMany(mappedBy = "postLiked")
    @JsonIgnore
    private List<LikePost> likePostList=new ArrayList<>();

    @Column(nullable = false)
    private boolean privatePost;

    @Column(nullable = false)
    private String postText;


    private String postImg;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private Integer likes;

    private Integer comments;
}
