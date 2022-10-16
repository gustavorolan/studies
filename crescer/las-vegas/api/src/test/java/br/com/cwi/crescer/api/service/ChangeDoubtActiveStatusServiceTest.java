package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.repository.DoubtRepository;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.util.DateAndTimeFactory;
import br.com.cwi.crescer.api.util.DoubtFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ChangeDoubtActiveStatusServiceTest {

    @InjectMocks
    private ChangeDoubtActiveStatusService changeDoubtActiveStatusService;

    @Mock
    private DoubtRepository doubtRepository;

    @Mock
    private FindDoubtWithThrow findDoubtWithThrow;

    @Mock
    private LocalDateAndTimeService localDateAndTimeService;

    @Mock
    private PermissionValidator permissionValidator;

    @Captor
    private ArgumentCaptor<Doubt> captor;

    @Test
    @DisplayName("Change doubt active status")
    void changeDoubtActiveStatus() {
      LocalDateTime dateTime = DateAndTimeFactory.getDateTime();

        Long id = 1L;
        Doubt doubt = DoubtFactory.getDoubt();
        Doubt doubtFinal = DoubtFactory.getDoubtBuilder()
                                .active(false)
                                .build();

        ResponseMessage responseMessage = ResponseMessage.builder()
                .response("You've changed successfully")
                .build();



        Mockito.when(findDoubtWithThrow.findByIdWithException(id)).thenReturn(doubt);


        ResponseMessage response = changeDoubtActiveStatusService.changeDoubtActiveStatus(id);

        Mockito.verify(findDoubtWithThrow).findByIdWithException(id);
        Mockito.verify(permissionValidator).validateLoggedUserPermission(doubt.getAuthor());

        Mockito.verify(doubtRepository).save(captor.capture());

        Doubt value = captor.getValue();

        Assertions.assertEquals(doubtFinal,value);
        Assertions.assertEquals(responseMessage,response);

    }

}