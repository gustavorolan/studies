package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FindAttachmentWithThrowTest {
    @InjectMocks
    private FindAttachmentWithThrow findAttachmentWithThrow;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Test
    @DisplayName("Should find attachment")
    void findAttachmentById() {
        String id = "1";

        Attachment attachment = AttachmentFactory.getAttachment();

        Mockito.when(attachmentRepository.findById(id))
                .thenReturn(Optional.ofNullable(attachment));

        Attachment value = findAttachmentWithThrow.findByIdWithException(id);

        Assertions.assertEquals(attachment, value);
    }

    @Test
    @DisplayName("Should throw exception whent attempting to find attachment")
    void findAttachmentByIdWithThrow() {
        String id = "2";

        Attachment attachment = AttachmentFactory.getAttachment();

        Mockito.when(attachmentRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findAttachmentWithThrow.findByIdWithException(id);
        });

        Mockito.verify(attachmentRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"Attachment does not exist\"", exception.getMessage());
    }
}
