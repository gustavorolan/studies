package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.CreateNewCommentRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Notification;
import br.com.cwi.crescer.api.model.Post;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.CommentRepository;
import br.com.cwi.crescer.api.repository.NotificationRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.finders.PostFinderById;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class CreateNewCommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostFinderById postFinderById;
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    public PutAndPostResponse post(@Valid CreateNewCommentRequest request) {
        UserAccount user = findUserAuthenticatedService.getUser();
        Comment comment = Comment.builder()
                .userAccount(user)
                .commentText(request.getComment())
                .build();

       Post post = postFinderById.findByIdWithException(request.getPostId());

       post.getCommentList().add(comment);

        Integer length = post.getCommentList().toArray().length;
        post.setComments(length);

        comment.setPostCommented(post);

       commentRepository.save(comment);

       Notification notification = Notification.builder()
               .notification(user.getUserName() + " comentou no seu post")
               .build();

       List<UserAccount> usersToNotify = post.getUserAccountList();

       usersToNotify.stream().forEach(userToNotify-> {
           userToNotify.getNotificationList().add(notification);
           userAccountRepository.save(userToNotify);
       });

       notificationRepository.save(notification);
        return PutAndPostResponse.builder()
                .response("Você comentou com sucesso").build();
    }
}
