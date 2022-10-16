package br.com.cwi.crescer.api.service.verifys;

import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VerifyIfIsPostAuthorService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    public void VerifyIfIsPostAuthor(Post post) {

        UserAccount userLogged = findUserAuthenticatedService.getUser();
        UserAccount author = post.getUserAccountList().get(0);
        if (!userLogged.equals(author)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autor do post não esta logado");
        }
    }
}
