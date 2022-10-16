package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.mapper.CommentMapper;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.CommentRepository;
import br.com.cwi.crescer.api.service.finder.FindCommentWithThrow;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeCommentStatusService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private FindCommentWithThrow findCommentWithThrow;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private FindDoubtWithThrow findDoubtWithThrow;


    public CommentResponse changeCommentStatus(Long commentId, Long doubtId) {

        Comment comment = findCommentWithThrow.findByIdAndActiveWithException(commentId, true);

        Doubt doubt = findDoubtWithThrow.findByIdAndActiveWithException(doubtId, true);

        permissionValidator.validateLoggedUserPermission(doubt.getAuthor());

        Boolean isRightResponse = comment.getIsRightResponse();

        comment.setIsRightResponse(!isRightResponse);

        doubt.setFinished(true);

        if (!isRightResponse) {
            doubt.setFinished(true);
        }

        commentRepository.save(comment);

        return commentMapper.toResponse(comment);
    }
}
