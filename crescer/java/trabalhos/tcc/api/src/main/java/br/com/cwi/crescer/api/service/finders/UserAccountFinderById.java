package br.com.cwi.crescer.api.service.finders;

import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserAccountFinderById {
    @Autowired
    private UserAccountRepository userAccountRepository;
    public UserAccount findByIdWithException(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User não existe"));
    }

}
