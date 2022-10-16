package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.repository.CommentRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.service.finder.FindCommentWithThrow;
import br.com.cwi.crescer.api.util.CommentFactory;
import br.com.cwi.crescer.api.util.DateAndTimeFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ChangeCommentActiveStatusServiceTest {
    @InjectMocks
    private ChangeCommentActiveStatusService changeCommentActiveStatusService;

    @Mock
    private CommentRepository commentRepository;


    @Mock
    private FindCommentWithThrow findCommentWithThrow;


    @Mock
    private PermissionValidator permissionValidator;

    @Captor
    private ArgumentCaptor<Comment> captor;

    @Test
    @DisplayName("Change Comment active status")
    void changeCommentActiveStatus() {

        LocalDateTime dateTime = DateAndTimeFactory.getDateTime();
        Long id = 1L;
        UserAccount user = UserAccountFactory.get();
        Comment commentInitial = CommentFactory.getBuilder()
                .author(user)
                .build();

        Comment commentFinal = CommentFactory.getBuilder().active(true)
                .author(user).build();

        ResponseMessage response = ResponseMessage.builder().response("You've changed successfully").build();


        Mockito.when(findCommentWithThrow.findByIdWithException(id)).thenReturn(commentInitial);


        ResponseMessage result= changeCommentActiveStatusService.changeCommentActiveStatus(id);


        Mockito.verify(findCommentWithThrow).findByIdWithException(id);
        Mockito.verify(permissionValidator).validateLoggedUserPermission(commentInitial.getAuthor());
        Mockito.verify(commentRepository).save(captor.capture());

        Comment value = captor.getValue();

        Assertions.assertEquals(response,result);
        Assertions.assertEquals(commentFinal,value);
    }
}