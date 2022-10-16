package br.com.cwi.crescer.api.service.verifys;

import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class VerifyIfPostAlreadyHasUserService {

    public void VerifyIfPostAlreadyHasUser(Post post, UserAccount user) {
        if (post.getUserAccountList().contains(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post ja tem esse usuario");
        }
    }
}