package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class AttachmentMapperTest {

    @InjectMocks
    private AttachmentMapper attachmentMapper;



    @Test
    @DisplayName("Should convert attachment to response")
    void toResponse() throws IOException {
        MockMultipartFile file = new
                MockMultipartFile("1", "1.png", "image/png", "png".getBytes());

        Attachment attachment = AttachmentFactory.getAttachment();
        attachment.setData(file.getBytes());

        AttachmentResponseData result = AttachmentFactory
                .getAttachmentResponseData();

        AttachmentResponseData attachmentResponseData = attachmentMapper
                .toResponse(file, attachment, attachment.getFileName());

        Assertions.assertEquals(result,attachmentResponseData);
    }

    @Test
    @DisplayName("Should convert attachment to response")
    void toResponseAttachment() throws IOException {
        MockMultipartFile file = new
                MockMultipartFile("1", "1.png", "image/png", "png".getBytes());

        Attachment attachment = AttachmentFactory.getAttachment();
        attachment.setData(file.getBytes());

        AttachmentResponseData result = AttachmentFactory
                .getAttachmentResponseData();
        result.setDownloadURL(null);
        result.setFileSize(0);

        AttachmentResponseData attachmentResponseData = attachmentMapper
                .toResponseAttachment(attachment);

        Assertions.assertEquals(result,attachmentResponseData);
    }
}