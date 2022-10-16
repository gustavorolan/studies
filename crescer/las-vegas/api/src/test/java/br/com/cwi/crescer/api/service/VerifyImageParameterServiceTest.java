package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.service.finder.FindAttachmentWithThrow;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VerifyImageParameterServiceTest {
    @InjectMocks
    private VerifyImageParameterService verifyImageParameterService;

    @Mock
    private FindAttachmentWithThrow findAttachmentWithThrow;

    @Test
    @DisplayName("Should return null if image parameter is blank")
    void verifyImageParameter() {

        Attachment attachment = verifyImageParameterService.verifyImageParameter("");

        Assertions.assertNull(attachment);


    }

    @Test
    @DisplayName("Should return attachment if image parameter is not blank")
    void verifyImageParameterAttachment() {
        Attachment attachmentToReturn = AttachmentFactory.getBuilderAttachment().id("um").build();

        Mockito.when(findAttachmentWithThrow.findByIdWithException(attachmentToReturn.getId()))
                .thenReturn(attachmentToReturn);

        Attachment attachment = verifyImageParameterService.verifyImageParameter(attachmentToReturn.getId());

        Mockito.verify(findAttachmentWithThrow).findByIdWithException(attachmentToReturn.getId());

        Assertions.assertEquals(attachmentToReturn,attachment);


    }
}