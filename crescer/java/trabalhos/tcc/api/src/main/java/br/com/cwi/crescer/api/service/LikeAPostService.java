package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.LikeAPostRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.LikePost;
import br.com.cwi.crescer.api.model.Notification;
import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.*;
import br.com.cwi.crescer.api.service.finders.PostFinderById;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeAPostService {
    @Autowired
    private PostFinderById postFinderById;
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private  PostRepository postRepository;

    public PutAndPostResponse like(LikeAPostRequest request) {
        Post post = postFinderById.findByIdWithException(request.getPostId());
        UserAccount user = findUserAuthenticatedService.getUser();
        List<LikePost> postList =  likeRepository.filterByPostId(request.getPostId(),user.getUserId());

        if(postList.isEmpty()){
            LikePost like = LikePost.builder()
                    .userAccount(user)
                    .build();

            post.getLikePostList().add(like);
            post.setLikes(post.getLikePostList().toArray().length);
            postRepository.save(post);
            like.setPostLiked(post);
            likeRepository.save(like);

            Notification notification = Notification.builder()
                    .notification(user.getUserName() + " curtiu seu post")
                    .build();

            List<UserAccount> usersToNotify = post.getUserAccountList();

            usersToNotify.stream().forEach(userToNotify-> {
                userToNotify.getNotificationList().add(notification);
                userAccountRepository.save(userToNotify);
            });

            notificationRepository.save(notification);

            return PutAndPostResponse.builder()
                    .response("Você curtiu com sucesso").build();
        }

        else{

            LikePost likePost = postList.get(0);
            post.setLikes(post.getLikePostList().toArray().length-1);
            postRepository.save(post);
            likeRepository.delete(likePost);
            return PutAndPostResponse.builder()
                    .response("Você descurtiu com sucesso").build();
        }

    }
}
