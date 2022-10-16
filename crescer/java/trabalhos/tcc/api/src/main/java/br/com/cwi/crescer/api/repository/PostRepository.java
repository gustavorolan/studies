package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(("select p from Post p where ?1 in p.userAccountList"))
    List<Post> findAllByUserAccountInUserAccountList(List<UserAccount> userAccountList);
}