package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.GetPassengerResponse;
import br.com.cwi.crescer.melevaai.mapper.GetPassengerMapper;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPassengerService {

  @Autowired
  private PassengerFinderByIdService passengerFinderByIdService;

  @Autowired
  private GetPassengerMapper getPassengerMapper;

  public GetPassengerResponse getPassenger(Long passengerId) {
    Passenger passenger = passengerFinderByIdService.findByIdWithException(passengerId);

    return getPassengerMapper.toResponse(passenger);
  }
}
