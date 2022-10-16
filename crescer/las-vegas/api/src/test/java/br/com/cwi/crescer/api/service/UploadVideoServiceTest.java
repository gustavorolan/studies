package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.model.VideoType;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.vimeo.VimeoEditVideoService;
import br.com.cwi.crescer.api.service.vimeo.VimeoUploadVideoService;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.VideoIsTooLongValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static br.com.cwi.crescer.api.model.InitialScore.SCORE;

@ExtendWith(MockitoExtension.class)
class UploadVideoServiceTest {
    @InjectMocks
    private UploadVideoService uploadVideoService;

    @Mock
    private VimeoUploadVideoService vimeoUploadVideoService;

    @Mock
    private VimeoEditVideoService vimeoEditVideoService;

    @Mock
    private VideoIsTooLongValidator videoIsTooLongValidator;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private UploadVideoInfoService uploadVideoInfoService;

    @Captor
    private ArgumentCaptor<Video> videoArgumentCaptor;

    @Test
    @DisplayName("Should upload a video")
    void uploadVideo() throws IOException {
       File file = new File("src/main/resources/testFile.mp4");
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("video.mp4", fileInputStream);
        String name = "Name";
        String desc = "Desc";
        String endPoint = "/endpoint";
        UserAccount userAccount = UserAccountFactory.get();
        String link = "/link";
        ResponseMessage message = ResponseMessage.builder().response("You've successfully uploaded").build();

        Video response = Video.builder()
                .author(userAccount)
                .description(desc)
                .endPoint(endPoint)
                .name(name)
                .link(link)
                .type(VideoType.COURSE)
                .score(SCORE.getScore())
                .active(true)
                .build();


        Mockito.when(vimeoUploadVideoService.upload(mockMultipartFile, name, desc)).thenReturn(endPoint);
        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(uploadVideoInfoService.getPlayerEmbedUrl(endPoint)).thenReturn(endPoint);

        ResponseMessage responseMessage = uploadVideoService.uploadVideo(mockMultipartFile, name, desc);

        Mockito.verify(vimeoUploadVideoService).upload(mockMultipartFile, name, desc);
        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(uploadVideoInfoService).getPlayerEmbedUrl(endPoint);
        Mockito.verify(videoRepository).save(videoArgumentCaptor.capture());

        Assertions.assertEquals(response,videoArgumentCaptor.getValue());
        Assertions.assertEquals(message,responseMessage);

    }
}