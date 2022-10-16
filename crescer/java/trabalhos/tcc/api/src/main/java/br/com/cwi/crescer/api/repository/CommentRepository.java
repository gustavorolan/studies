package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.postCommented.postId=?1")
    List<Comment> findAllPostComments(Long postId);
}
