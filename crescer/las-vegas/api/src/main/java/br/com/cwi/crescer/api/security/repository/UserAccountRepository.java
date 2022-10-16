package br.com.cwi.crescer.api.security.repository;

import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Page<UserAccount> findAll(Pageable pageable);

    Optional<UserAccount> findByEmail(String email);

    Optional<UserAccount> findByIdAndActive(Long id, Boolean active);

    @Query("select u from UserAccount u where u.id not in ?1")
    Optional<Page<UserAccount>> findOthers(List<Long> ids, Pageable pageable);
}
