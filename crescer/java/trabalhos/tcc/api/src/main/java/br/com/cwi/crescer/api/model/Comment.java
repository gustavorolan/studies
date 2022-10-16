package br.com.cwi.crescer.api.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "commentId")
@EqualsAndHashCode(of = "commentId")
@Setter
@Getter
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId")
    private Post postCommented;

    @Column(nullable = false)
    private String commentText;

}
