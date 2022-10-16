package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindUserWithThrow {
    @Autowired
    private UserAccountRepository userAccountRepository;

    private static final String RESPONSE = "User does not exist";

    public UserAccount findByIdWithException(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<UserAccount> findByIfMyTeamsContainsNot(Pageable pageable, List<Long> ids) {
        return userAccountRepository.findOthers(ids, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<UserAccount> findByIdContainsNotWithException(Pageable pageable, List<Long> ids) {
        return userAccountRepository.findOthers(ids, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public UserAccount findByIdAndActiveWithException(Long id, Boolean active) {
        return userAccountRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public UserAccount findByEmailWithException(String email) {
        return userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}
