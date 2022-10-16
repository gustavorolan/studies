package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByEmail(String email);
    UserAccount findByNickname(String nickname);

    @Query("select u from UserAccount u where " +
            " (u.userName like %?1% or u.email like %?1%  " +
            "or u.nickname like %?1%)and not u.userId=?2 ")
    List<UserAccount> filterAllPeopleEmailName(String nameOrEmail,Long userId);
}