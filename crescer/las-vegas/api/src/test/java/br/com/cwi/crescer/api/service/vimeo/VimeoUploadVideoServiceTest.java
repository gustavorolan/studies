package br.com.cwi.crescer.api.service.vimeo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VimeoUploadVideoServiceTest {

    @InjectMocks
    private VimeoUploadVideoService vimeoUploadVideoService;

    @Mock
    private VimeoEditVideoService vimeoEditVideoService;

//    @Test
//    @DisplayName("Should upload a video")
//    void uploadVideo() throws IOException {
//        File file = new File("src/main/resources/testFile.mp4");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("video.mp4", fileInputStream);
//
//        String name = "novo nome";
//        String desc = "nova descrição";
//        String enpoint = "/videos/707699077";
//
//        vimeoUploadVideoService.upload(mockMultipartFile, name, desc);
//
//        Mockito.verify(vimeoEditVideoService).edit(enpoint, name, desc);
//    }
}
