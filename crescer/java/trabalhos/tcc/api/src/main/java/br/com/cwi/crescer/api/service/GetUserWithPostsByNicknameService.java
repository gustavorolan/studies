package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.PostResponseWithUser;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.controller.response.UserWithPostsResponse;
import br.com.cwi.crescer.api.mapper.PostListMapper;
import br.com.cwi.crescer.api.mapper.UserResponseMapper;
import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetUserWithPostsByNicknameService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserResponseMapper userResponseMapper;
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private PostListMapper postListMapper;


    public UserWithPostsResponse get(String nickname) {
        UserAccount userFriend = userAccountRepository.findByNickname(nickname);
        UserAccountResponse response = userResponseMapper.toResponse(userFriend);
        UserWithPostsResponse userWithPostsResponse = userResponseMapper.toResponseWithPosts(userFriend);

        UserAccount user = findUserAuthenticatedService.getUser();
        List<Friendship> friendshipList = friendshipRepository
                .filterFriendsByUserToUndoFriendship(userFriend.getUserId(), user.getUserId());


        if(userFriend.equals(user)){
            List<Post> postList= userFriend.getPostList();
            List<PostResponseWithUser> postListWithoutUser = postList.stream()
                    .map(post -> postListMapper.postWithoutUserToResponse(post))
                    .collect(Collectors.toList());

            userWithPostsResponse.setPostResponseList(postListWithoutUser);


            return userWithPostsResponse;

        } else if(friendshipList.isEmpty()){
            List<PostResponseWithUser> postList = userFriend.getPostList()
                    .stream()
                    .filter(post -> post.isPrivatePost()==false)
                    .map(post -> postListMapper.postWithoutUserToResponse(post) )
                    .collect(Collectors.toList());

            userWithPostsResponse.setPostResponseList(postList);

            return userWithPostsResponse;

        }

        List<Post> postList= userFriend.getPostList();
        List<PostResponseWithUser> postListWithoutUser = postList.stream()
                .map(post -> postListMapper.postWithoutUserToResponse(post))
                .collect(Collectors.toList());

        userWithPostsResponse.setPostResponseList(postListWithoutUser);


        return userWithPostsResponse;
    }
}
