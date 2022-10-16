package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.repository.CommentRepository;
import br.com.cwi.crescer.api.service.finder.FindCommentWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeCommentActiveStatusService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FindCommentWithThrow findCommentWithThrow;

    @Autowired
    private LocalDateAndTimeService localDateAndTimeService;

    @Autowired
    private PermissionValidator permissionValidator;

    public ResponseMessage changeCommentActiveStatus(Long commentId) {
        Comment comment = findCommentWithThrow.findByIdWithException(commentId);

        permissionValidator.validateLoggedUserPermission(comment.getAuthor());

        Boolean isCommentActive = comment.getActive();

        comment.setActive(!isCommentActive);

        commentRepository.save(comment);

        return ResponseMessage.builder().response("You've changed successfully").build();
    }
}
