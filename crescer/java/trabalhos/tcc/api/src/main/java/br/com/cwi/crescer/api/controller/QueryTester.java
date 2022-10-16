package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.GetUserByNicknameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class QueryTester {


        @Autowired
        private PostRepository postRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
        @GetMapping("/{nickname}")
        public List<Post> getByNickName (@PathVariable String nickname){
            UserAccount userAccount = userAccountRepository.findByNickname(nickname);
            return postRepository.findAllByUserAccountInUserAccountList(List.of(userAccount));

}}
