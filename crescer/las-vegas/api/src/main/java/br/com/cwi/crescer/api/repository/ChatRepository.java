package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
@Query("select c from Chat c where (c.userOne=?1 and c.userTwo=?2)" +
        " or (c.userOne=?2 and c.userTwo=?1)")
    Optional<Chat>findChatByUsers(UserAccount userOne, UserAccount userTwo);

@Query("select c from Chat c where (c.userOne=?1) or (c.userTwo=?1) order by c.lastMessage desc")
    Optional<List<Chat>>findMessageByUser(UserAccount user);
}
