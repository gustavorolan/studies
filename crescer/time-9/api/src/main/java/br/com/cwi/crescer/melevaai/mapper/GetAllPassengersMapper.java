package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.PassengerResponse;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetAllPassengersMapper {
        public  PassengerResponse toResponse(Passenger entity){
        return new ModelMapper().map(entity,PassengerResponse.class);
    }

}
