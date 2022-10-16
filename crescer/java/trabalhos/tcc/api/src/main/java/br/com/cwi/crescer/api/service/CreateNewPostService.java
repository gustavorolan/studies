package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.CreateNewPostRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.mapper.CreateNewPostMapper;
import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
public class CreateNewPostService extends br.com.cwi.crescer.api.mapper.CreateNewPostMapper {

    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CreateNewPostMapper createNewPostMapper;


    public PutAndPostResponse post(@Valid CreateNewPostRequest request) {
        UserAccount user = findUserAuthenticatedService.getUser();

        Post post = createNewPostMapper.toEntity(request);
        post.setDateTime(LocalDateTime.now());

        post.getUserAccountList().add(user);
        user.getPostList().add(post);

        postRepository.save(post);

        return PutAndPostResponse.builder()
                .response(user.getUserName()+ ", você criou o post com suceso")
                .build();
    }

}
