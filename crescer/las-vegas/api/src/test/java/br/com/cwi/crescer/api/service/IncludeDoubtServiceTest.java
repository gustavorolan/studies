package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.DoubtRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.mapper.DoubtMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.AttachmentRepository;
import br.com.cwi.crescer.api.repository.DoubtRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.DoubtFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class IncludeDoubtServiceTest {
    @InjectMocks
    private IncludeDoubtService includeDoubtService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private DoubtMapper doubtMapper;

    @Mock
    private DoubtRepository doubtRepository;

    @Mock
    private ParametersRegexValidator parametersRegexValidator;

    @Captor
    private ArgumentCaptor<Doubt> doubtArgumentCaptor;

    @Test
    @DisplayName("Should include a doubt service")
    void includeDoubt() {
        Attachment attachment = AttachmentFactory.getAttachment();
        UserAccount userAccount = UserAccountFactory.get();
        Doubt doubt = DoubtFactory.getDoubt();
        DoubtRequest doubtRequest = DoubtFactory.getDoubtRequest();
        Doubt doubtSaved = DoubtFactory.getDoubtBuilder()
                .author(userAccount)
                .dateTimeCreation(LocalDateTime.now())
                .active(true)
                .finished(false)
                .build();

        ResponseMessage responseMessage = ResponseMessage.builder()
                .response("You've included successfully")
                .build();


        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(doubtMapper.toEntity(doubtRequest)).thenReturn(doubt);
        Mockito.when(attachmentRepository.findById(attachment.getId()))
                .thenReturn(Optional.of(attachment));

        ResponseMessage result = includeDoubtService.includeDoubt(doubtRequest, attachment.getId());

        Mockito.verify(parametersRegexValidator).verifyIfImageIdIsValid(attachment.getId());
        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(attachmentRepository).findById(attachment.getId());
        Mockito.verify(doubtMapper).toEntity(doubtRequest);
        Mockito.verify(doubtRepository).save(doubtArgumentCaptor.capture());

        Assertions.assertEquals( doubtSaved,doubtArgumentCaptor.getValue());

    }
}