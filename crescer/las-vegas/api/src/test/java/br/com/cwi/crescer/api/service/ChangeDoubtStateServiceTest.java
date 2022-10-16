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
class ChangeDoubtStateServiceTest {
    @InjectMocks
    private ChangeDoubtStateService changeDoubtStateService;
    @Mock
    private DoubtRepository doubtRepository;

    @Mock
    private FindDoubtWithThrow findDoubtWithThrow;

    @Mock
    private PermissionValidator permissionValidator;


    @Captor
    private ArgumentCaptor<Doubt> captor;
    @Test
    @DisplayName("Change Doubt state")
    void changeDoubtState() {
        Long id = 1L;
        LocalDateTime dateTime = DateAndTimeFactory.getDateTime();
        Doubt doubt = DoubtFactory.getDoubt();
        Doubt finalDoubt = DoubtFactory.getDoubtBuilder().finished(false).build();
        ResponseMessage response = ResponseMessage.builder().response("You've changed successfully").build();


        Mockito.when(findDoubtWithThrow.findByIdAndActiveWithException(id, true)).thenReturn(doubt);

        ResponseMessage responseMessage = changeDoubtStateService.changeDoubtState(id);

        Mockito.verify(findDoubtWithThrow).findByIdAndActiveWithException(id, true);
        Mockito.verify(doubtRepository).save(captor.capture());
        Mockito.verify(permissionValidator).validateLoggedUserPermission(doubt.getAuthor());

        Doubt value = captor.getValue();

        Assertions.assertEquals(response,responseMessage);
        Assertions.assertEquals(finalDoubt,value);
    }
}