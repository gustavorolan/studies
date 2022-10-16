package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.DoubtResponse;
import br.com.cwi.crescer.api.mapper.DoubtMapper;
import br.com.cwi.crescer.api.model.Doubt;
import br.com.cwi.crescer.api.service.finder.FindDoubtWithThrow;
import br.com.cwi.crescer.api.util.DoubtFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDetailedDoubtServiceTest {

    @InjectMocks
    private GetDetailedDoubtService getDetailedDoubtService;

    @Mock
    private DoubtMapper doubtMapper;

    @Mock
    private FindDoubtWithThrow findDoubtWithThrow;

    @Test
    @DisplayName("Should return doubts detailed")
    void getDetailedDoubt() {
        Long id = 1L;
        Doubt doubt = DoubtFactory.getDoubt();
        DoubtResponse doubtResponse = DoubtFactory.getDoubtResponse();

        Mockito.when(findDoubtWithThrow.findByIdAndActiveWithException(id, true)).thenReturn(doubt);
        Mockito.when(doubtMapper.toResponse(doubt)).thenReturn(doubtResponse);


        DoubtResponse detailedDoubt = getDetailedDoubtService.getDetailedDoubt(id);

        Mockito.verify(doubtMapper).toResponse(doubt);
        Mockito.verify(findDoubtWithThrow).findByIdAndActiveWithException(id, true);


        Assertions.assertEquals(doubtResponse,detailedDoubt);
    }
}