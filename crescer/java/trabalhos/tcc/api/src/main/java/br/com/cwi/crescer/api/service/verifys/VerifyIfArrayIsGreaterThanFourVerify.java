package br.com.cwi.crescer.api.service.verifys;

import br.com.cwi.crescer.api.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class VerifyIfArrayIsGreaterThanFourVerify {
    public void VerifyIfArrayIsGreaterThanFour(Post post) {

        if (post.getUserAccountList().toArray().length>4) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Limite maximo de jogadores");
        }
    }
}
