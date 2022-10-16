package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.mapper.CommentMapper;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.CommentRepository;
import br.com.cwi.crescer.api.service.finder.FindCommentWithThrow;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.util.CommentFactory;
import br.com.cwi.crescer.api.util.DoubtFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChangeCommentStatusServiceTest {

    @InjectMocks
    private ChangeCommentStatusService changeCommentStatusService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private FindCommentWithThrow findCommentWithThrow;


    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private FindDoubtWithThrow findDoubtWithThrow;

    @Captor
    private ArgumentCaptor<Comment> captor;

    @Test
    @DisplayName("Change comment status")
    void changeCommentStatus() {
        Doubt doubt = DoubtFactory.getDoubt();
        Comment comment = CommentFactory.getBuilder().isRightResponse(false).build();
        Comment commentFinal = CommentFactory.getBuilder()
                .isRightResponse(true)
                .build();
        CommentResponse commentResponse = CommentResponse.builder()
                .isRightResponse(true)
                .build();

        Mockito.when(findDoubtWithThrow
                        .findByIdAndActiveWithException(doubt.getId(), true))
                .thenReturn(doubt);


        Mockito.when(commentMapper
                        .toResponse(comment))
                .thenReturn(commentResponse);

        Mockito.when(findCommentWithThrow.findByIdAndActiveWithException(comment.getId(), true))
                .thenReturn(comment);


        changeCommentStatusService.changeCommentStatus(comment.getId(), comment.getId());

        Mockito.verify(findCommentWithThrow)
                .findByIdAndActiveWithException(comment.getId(),true);

        Mockito.verify(findDoubtWithThrow)
                .findByIdAndActiveWithException(doubt.getId(),true);

        Mockito.verify(permissionValidator)
                .validateLoggedUserPermission(doubt.getAuthor());

        Mockito.verify(commentMapper).toResponse(comment);

        Mockito.verify(commentRepository).save(captor.capture());

        Comment value = captor.getValue();

        Assertions.assertEquals(commentFinal,value);
    }
}