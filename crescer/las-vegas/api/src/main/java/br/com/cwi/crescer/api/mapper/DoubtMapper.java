package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.DoubtRequest;
import br.com.cwi.crescer.api.controller.response.DoubtResponse;
import br.com.cwi.crescer.api.model.Doubt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DoubtMapper {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AttachmentMapper attachmentMapper;

    @Autowired
    CommentMapper commentMapper;

    public Doubt toEntity(DoubtRequest request) {
        return new ModelMapper().map(request, Doubt.class);
    }

    public DoubtResponse toResponse(Doubt doubt) {

        DoubtResponse response = DoubtResponse.builder()
                .id(doubt.getId())
                .description(doubt.getDescription())
                .finished(doubt.getFinished())
                .active(doubt.getActive())
                .author(userMapper.toResponse(doubt.getAuthor()))
                .dateTimeCreation(doubt.getDateTimeCreation())
                .comments(doubt.getComments().stream().map( commentMapper::toResponse).collect(Collectors.toList()))
                .build();
        if (doubt.getImage() != null) {
            response.setImage(attachmentMapper.toResponseAttachment(doubt.getImage()));
        }
        return response;
    }
}