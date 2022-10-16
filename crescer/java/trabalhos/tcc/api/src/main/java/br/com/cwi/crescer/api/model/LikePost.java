package br.com.cwi.crescer.api.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "likeId")
@EqualsAndHashCode(of = "likeId")
@Setter
@Getter
@Builder
@Entity
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "postId")
    private Post postLiked;

}
