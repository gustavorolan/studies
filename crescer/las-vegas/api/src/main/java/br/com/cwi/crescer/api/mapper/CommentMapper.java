package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.CommentRequest;
import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    @Autowired
    AttachmentMapper attachmentMapper;

    @Autowired
    UserMapper userMapper;

    public Comment toEntity(CommentRequest request) {
        return new ModelMapper().map(request, Comment.class);
    }

    public CommentResponse toResponse(Comment comment){
         CommentResponse response = CommentResponse.builder()
                .id(comment.getId())
                .description(comment.getDescription())
                .isRightResponse(comment.getIsRightResponse())
                .active(comment.getActive())
                .author(userMapper.toResponse(comment.getAuthor()))
                 .dateTimeCreation(comment.getDateTimeCreation())
                .build();
        if (comment.getImage() != null) {
            response.setImage(attachmentMapper.toResponseAttachment(comment.getImage()));
        }
         return response;
    }
}
