package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.CommentRequest;
import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.CommentFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentMapperTest {

    @InjectMocks
    private CommentMapper mapper;

    @Mock
    private AttachmentMapper attachmentMapper;

    @Mock
    private UserMapper userMapper;

    @Test
    @DisplayName("Should convert comment to entity")
    void toEntity() {
        Comment comment = CommentFactory.get();
                comment.setId(null);

        CommentRequest commentRequest = CommentFactory.getCommentRequest();

        Comment result = mapper.toEntity(commentRequest);

        Assertions.assertEquals(comment,result);
    }

    @Test
    @DisplayName("Should convert comment to response")
    void toResponse() {
        Attachment attachment = AttachmentFactory.getAttachment();
        UserAccount userAccount = UserAccountFactory.get();
        Comment comment = CommentFactory.getBuilder()
                .image(attachment)
                .author(userAccount)
                .build();


        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        UserResponse userResponse = UserAccountFactory.getUserResponse();
        CommentResponse commentResponse = CommentFactory.getBuilderCommentResponse()
                .image(attachmentResponseData)
                .author(userResponse)
                .build();


        Mockito.when(attachmentMapper.toResponseAttachment(comment.getImage())).thenReturn(commentResponse.getImage());
        Mockito.when(userMapper.toResponse(comment.getAuthor())).thenReturn(commentResponse.getAuthor());

        CommentResponse result = mapper.toResponse(comment);

        Mockito.verify(attachmentMapper).toResponseAttachment(comment.getImage());
        Mockito.verify(userMapper).toResponse(comment.getAuthor());

        Assertions.assertEquals(commentResponse,result);

    }
}