package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.DoubtRequest;
import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.controller.response.DoubtResponse;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.CommentFactory;
import br.com.cwi.crescer.api.util.DoubtFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class DoubtMapperTest {
    @InjectMocks
    private DoubtMapper doubtMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AttachmentMapper attachmentMapper;

    @Mock
    private CommentMapper commentMapper;

    @Test
    @DisplayName("Should return a Doubt")
    void toEntity() {
        Doubt doubt = DoubtFactory.getDoubtBuilder()
                .id(null)
                .build();
        DoubtRequest request = DoubtFactory.getDoubtRequest();

        Doubt result = doubtMapper.toEntity(request);

        Assertions.assertEquals(doubt,result);

    }

    @Test
    @DisplayName("Should return a DoubtResponse")
    void toResponse() {
        Attachment attachment = AttachmentFactory.getAttachment();
        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        UserAccount userAccount = UserAccountFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();
        Comment comment = CommentFactory.get();
        CommentResponse commentResponse = CommentFactory.getCommentResponse();

        Doubt doubt = DoubtFactory.getDoubtBuilder()
                .author(userAccount)
                .image(attachment)
                .comments(List.of(comment))
                .build();

        DoubtResponse response = DoubtFactory.getDoubtBuilderResponse()
                .author(userResponse)
                .image(attachmentResponseData)
                .comments(List.of(commentResponse))
                .build();

        Mockito.when(userMapper.toResponse(userAccount)).thenReturn(userResponse);
        Mockito.when(attachmentMapper.toResponseAttachment(doubt.getImage())).thenReturn(attachmentResponseData);
        Mockito.when(commentMapper.toResponse(comment)).thenReturn(commentResponse);

        DoubtResponse result = doubtMapper.toResponse(doubt);

        Mockito.verify(userMapper).toResponse(userAccount);
        Mockito.verify(attachmentMapper).toResponseAttachment(doubt.getImage());
        Mockito.verify(commentMapper).toResponse(comment);

        Assertions.assertEquals(response,result);

    }
}