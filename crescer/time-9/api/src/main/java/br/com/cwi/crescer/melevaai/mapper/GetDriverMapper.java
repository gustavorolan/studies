package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.GetDriverResponse;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetDriverMapper {

  public GetDriverResponse toResponse(Driver driver) {
    return new ModelMapper().map(driver, GetDriverResponse.class);
  }
}
