package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("select f from Friendship f where" +
            " (f.userAccount.userId=?1 or f.userFriendShip.userId=?1)" +
            "and f.relation=?2 ")
    List<Friendship> filterFriendsByUser(Long userId, Relation relation);

    @Query("select f from Friendship f where " +
            "((f.userAccount.userId=?1 " +
            "and f.userFriendShip.userId=?2)" +
            "or(f.userAccount.userId=?2 " +
            "and f.userFriendShip.userId=?1))")
    List<Friendship> filterFriendsByUserToUndoFriendship(Long userId,Long userToUndo);

    @Query("select f from Friendship f where" +
            " (f.userAccount.userId=?1" +
            " or f.userFriendShip.userId=?1) ")
    List<Friendship> filterFriendsWithoutRelationByUser(Long userId);


}
