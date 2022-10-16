package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.CommentRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.mapper.CommentMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import br.com.cwi.crescer.api.repository.DoubtRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.CommentFactory;
import br.com.cwi.crescer.api.util.DoubtFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class IncludeCommentInDoubtServiceTest {

    @InjectMocks
    private IncludeCommentInDoubtService includeCommentInDoubtService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private DoubtRepository doubtRepository;

    @Mock
    private ParametersRegexValidator parametersRegexValidator;

    @Mock
    private FindDoubtWithThrow findDoubtWithThrow;

    @Captor
    private ArgumentCaptor<Doubt> doubtArgumentCaptor;

    @Test
    @DisplayName("Include comment in doubt")
    void includeCommentInDoubt() {
        ResponseMessage responseMessage = ResponseMessage.builder().response("You've Included successfully").build();

        UserAccount userAccount = UserAccountFactory.get();
        Attachment attachment = AttachmentFactory.getAttachment();
        Doubt doubt = DoubtFactory.getDoubtBuilder().comments(new ArrayList<>()).build();
        CommentRequest commentRequest = CommentFactory.getCommentRequest();
        Comment comment = CommentFactory.get();
        Comment commentSaved = Comment.builder()
                .author(userAccount)
                .doubt(doubt)
                .dateTimeCreation(LocalDateTime.now())
                .isRightResponse(false)
                .active(true)
                .build();

        Doubt doubtSaved = DoubtFactory.getDoubtBuilder().comments(List.of(commentSaved)).build();


        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(commentMapper.toEntity(commentRequest)).thenReturn(comment);
        Mockito.when(attachmentRepository.findById(attachment.getId())).thenReturn(Optional.of(attachment));
        Mockito.when(findDoubtWithThrow.findByIdAndActiveWithException(doubt.getId(), true)).thenReturn(doubt);

        ResponseMessage result = includeCommentInDoubtService
                .includeCommentInDoubt(commentRequest, doubt.getId(), attachment.getId());

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(commentMapper).toEntity(commentRequest);
        Mockito.verify(attachmentRepository).findById(attachment.getId());
        Mockito.verify(findDoubtWithThrow).findByIdAndActiveWithException(doubt.getId(), true);
        Mockito.verify(parametersRegexValidator).verifyIfImageIdIsValid(attachment.getId());
        Mockito.verify(doubtRepository).save(doubtArgumentCaptor.capture());

        Assertions.assertEquals(doubtSaved,doubtArgumentCaptor.getValue());
        Assertions.assertEquals(responseMessage,result);
    }
}