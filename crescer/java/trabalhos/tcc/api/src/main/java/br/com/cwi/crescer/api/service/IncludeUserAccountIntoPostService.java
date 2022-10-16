package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.IncludeUserAccountIntoPostRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.finders.PostFinderById;
import br.com.cwi.crescer.api.service.finders.UserAccountFinderById;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import br.com.cwi.crescer.api.service.verifys.VerifyIfArrayIsGreaterThanFourVerify;
import br.com.cwi.crescer.api.service.verifys.VerifyIfIsPostAuthorService;
import br.com.cwi.crescer.api.service.verifys.VerifyIfPostAlreadyHasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncludeUserAccountIntoPostService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostFinderById postFinderById;
    @Autowired
    private UserAccountFinderById userAccountFinderById;
    @Autowired
    private VerifyIfPostAlreadyHasUserService verifyIfPostAlreadyHasUserService;
    @Autowired
    private VerifyIfIsPostAuthorService verifyIfIsPostAuthorService;
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private VerifyIfArrayIsGreaterThanFourVerify verifyIfArrayIsGreaterThanFourVerify;

    public PutAndPostResponse includeUserAccount(IncludeUserAccountIntoPostRequest request) {

        UserAccount userAccount = findUserAuthenticatedService.getUser();
        Post post =  postFinderById.findByIdWithException(request.getIdPost());

        verifyIfArrayIsGreaterThanFourVerify.VerifyIfArrayIsGreaterThanFour(post);
        verifyIfPostAlreadyHasUserService.VerifyIfPostAlreadyHasUser(post,userAccount);


        post.getUserAccountList().add(userAccount);
        userAccount.getPostList().add(post);

        postRepository.save(post);

        return PutAndPostResponse.builder()
                .response("Você incluiu "+ userAccount.getUserName()+" no seu post")
                .build();

    }
}
