package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.cloud.ImageValidate;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {
    @InjectMocks
    private UserAccountService userAccountService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private ImageValidate imageValidate;

    @Mock
    private AttachmentService attachmentService;

    @Captor
    private ArgumentCaptor<UserAccount> userAccountArgumentCaptor;

    @Test
    @DisplayName("Should Create a user")
    void include() throws IOException {
        RegisterUserRequest userRequest = UserAccountFactory.getUserRequest();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("1", "1.png", "image/png", "png".getBytes());

        Attachment attachment
            = new Attachment(mockMultipartFile.getOriginalFilename(),
                mockMultipartFile.getContentType(),
                mockMultipartFile.getBytes());

        UserAccount userAccount = UserAccountFactory.get();
        ResponseMessage responseMessage = ResponseMessage.builder()
                .response("User created successfully").build();

        Mockito.when(userMapper.toEntity(userRequest)).thenReturn(userAccount);

        Mockito.when(attachmentService.saveAttachment(mockMultipartFile))
                .thenReturn(attachment);

        ResponseMessage result = userAccountService.include(userRequest, mockMultipartFile);

        Mockito.verify(userMapper).toEntity(userRequest);
        Mockito.verify(userAccountRepository).save(userAccountArgumentCaptor.capture());
        Mockito.verify(imageValidate).imageValidator(mockMultipartFile.getBytes());

        Assertions.assertEquals(userAccount,userAccountArgumentCaptor.getValue());
        Assertions.assertEquals(responseMessage,result);
    }
}