package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.EvaluationResponse;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.EvaluationFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.util.VideoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EvaluationMapperTest {
    @InjectMocks
    private EvaluationMapper evaluationMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private VideoMapper videoMapper;

    @Test
    @DisplayName("Should return a EvaluationResponse")
    void toResponse() {
        UserAccount userAccount = UserAccountFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();
        Video video = VideoFactory.get();
        VideoResponse videoResponse = VideoFactory.getVideoResponse();

        Evaluation evaluation = EvaluationFactory.getBuilder()
                .userAccount(userAccount)
                .video(video)
                .build();

        EvaluationResponse evaluationResponse = EvaluationFactory.getBuilderResponse()
                .userAccount(userResponse)
                .video(videoResponse)
                .build();

        Mockito.when(userMapper.toResponse(userAccount)).thenReturn(userResponse);
        Mockito.when(videoMapper.toResponse(video)).thenReturn(videoResponse);

        EvaluationResponse result = evaluationMapper.toResponse(evaluation);

        Mockito.verify(userMapper).toResponse(userAccount);
        Mockito.verify(videoMapper).toResponse(video);

        Assertions.assertEquals(evaluationResponse,result);
    }
}