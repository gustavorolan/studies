package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.GetAllDriversResponse;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetAllDriversMapper {
    public  GetAllDriversResponse toResponse(Driver driver) {
        return new ModelMapper().map(driver, GetAllDriversResponse.class);
    }
}
