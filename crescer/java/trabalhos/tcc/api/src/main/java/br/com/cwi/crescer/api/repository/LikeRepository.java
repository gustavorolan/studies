package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikePost, Long> {
    @Query("select l from LikePost l where l.postLiked.postId=?1 and l.userAccount.userId=?2")
    List<LikePost> filterByPostId(Long postID,Long user);

    @Query("select l from LikePost l where l.postLiked.postId=?1")
    List<LikePost> filterAllLikesOfPostId(Long postID);

}
