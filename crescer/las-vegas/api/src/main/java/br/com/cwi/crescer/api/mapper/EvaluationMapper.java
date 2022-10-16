package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.EvaluationResponse;
import br.com.cwi.crescer.api.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvaluationMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    public EvaluationResponse toResponse(Evaluation evaluation) {
        return EvaluationResponse.builder()
                .id(evaluation.getId())
                .comment(evaluation.getComment())
                .score(evaluation.getScore())
                .dateTimeCreation(evaluation.getDateTimeCreation())
                .dateTimeUpdate(evaluation.getDateTimeUpdate())
                .userAccount(userMapper.toResponse(evaluation.getUserAccount()))
                .video(videoMapper.toResponse(evaluation.getVideo()))
                .build();
    }
}
