package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.GetPassengerResponse;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetPassengerMapper {

  public GetPassengerResponse toResponse(Passenger passenger) {
    return new ModelMapper().map(passenger, GetPassengerResponse.class);
  }

}
