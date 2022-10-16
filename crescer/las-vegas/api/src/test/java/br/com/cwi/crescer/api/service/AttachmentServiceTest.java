package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.mapper.AttachmentMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import br.com.cwi.crescer.api.service.cloud.ImageValidate;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.validator.MagicNumberValidator;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AttachmentServiceTest {

    @InjectMocks
    private AttachmentService attachmentService;

    @Mock
    private AttachmentService attachmentServiceMock;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private AttachmentMapper attachmentMapper;

    @Mock
    private ParametersRegexValidator parametersRegexValidator;

    @Mock
    private MagicNumberValidator magicNumberValidator;

    @Mock
    private ImageValidate imageValidate;

    @Captor
    private ArgumentCaptor<Attachment> attachmentArgumentCaptor;

    @Test
    @DisplayName("Should save attachment correctly")
    void saveAttachment() throws IOException {
        File file = new File("src/main/resources/teste.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("video.mp4", fileInputStream);

        Attachment attachment
                = new Attachment(mockMultipartFile.getOriginalFilename(),
                mockMultipartFile.getContentType(),
                mockMultipartFile.getBytes());

        attachmentService.saveAttachment(mockMultipartFile);

        Mockito.verify(parametersRegexValidator).validateAttachmentFileName(mockMultipartFile.getOriginalFilename());
        Mockito.verify(parametersRegexValidator).validateAttachmentContentType(mockMultipartFile.getContentType());
        Mockito.verify(magicNumberValidator).validateAttachment(mockMultipartFile);

        Mockito.verify(attachmentRepository).save(attachmentArgumentCaptor.capture());

        Assertions.assertEquals(attachment, attachmentArgumentCaptor.getValue());
    }

    @Test
    @DisplayName("Should get attachment correctly")
    void getAttachment() {
        String fileId = "1";

        Attachment attachment = AttachmentFactory.getAttachment();

        Mockito.when(attachmentRepository.findById(fileId))
                .thenReturn(Optional.ofNullable(attachment));

        Attachment value = attachmentService.getAttachment(fileId);

        Mockito.verify(attachmentRepository).findById(fileId);

        Assertions.assertEquals(attachment, value);
    }

    @Test
    @DisplayName("Should throw error whent attempting to get attachment correctly")
    void getAttachmentWithError() {
        String fileId = "1";

        Mockito.when(attachmentRepository.findById(fileId))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            attachmentService.getAttachment(fileId);
        });

        Mockito.verify(attachmentRepository).findById(fileId);

        Assertions.assertEquals("404 NOT_FOUND \"Image not found\"", exception.getMessage());
    }
}