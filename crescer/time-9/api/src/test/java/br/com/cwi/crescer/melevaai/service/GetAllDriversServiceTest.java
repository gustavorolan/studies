package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.GetAllDriversResponse;
import br.com.cwi.crescer.melevaai.mapper.GetAllDriversMapper;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllDriversServiceTest {
    @InjectMocks
    private GetAllDriversService getAllDriversService;

    @Mock
    private GetAllDriversMapper getAllDriversMapper;

    @Mock
    private DriverRepository driverRepository;

    @Test
    @DisplayName("Should return an driver response when interact with a driver list")
    void getAllDriversServiceTest() {

        Driver driverOne = DriverFactory.getBuilder().build();
        List<Driver> driverList = new ArrayList<Driver>();
        driverList.add(driverOne);


        GetAllDriversResponse allDriversResponse =  new GetAllDriversResponse();
        allDriversResponse.setNameDriver("driver testes");
        allDriversResponse.setBalance(new BigDecimal("20"));
        allDriversResponse.setPersonStatus(PersonStatus.LIVRE);

        List<GetAllDriversResponse> allDriversResponseList = new ArrayList<GetAllDriversResponse>();
        allDriversResponseList.add(allDriversResponse);



        when(driverRepository.findAll())
                .thenReturn(driverList);


        when(getAllDriversMapper.toResponse(driverOne))
                .thenReturn(allDriversResponse);



        List<GetAllDriversResponse> result = getAllDriversService.get();

        verify(getAllDriversMapper).toResponse(driverOne);
        verify(driverRepository).findAll();

        assertEquals(result,allDriversResponseList);

    }
}