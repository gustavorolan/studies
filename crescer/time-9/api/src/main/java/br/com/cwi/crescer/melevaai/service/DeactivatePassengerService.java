package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.DeactivatePassengerResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPersonStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeactivatePassengerService {

  @Autowired
  private PassengerRepository passengerRepository;

  @Autowired
  private VerifyPersonStatusService verifyPersonStatusService;

  @Autowired
  private PassengerFinderByIdService passengerFinderByIdService;


  public DeactivatePassengerResponse deactivatePassanger(Long driverId) {
    Passenger passenger = passengerFinderByIdService.findByIdWithException(driverId);

    verifyPersonStatusService.ifPassengerIsNotLivreThrowsAnException(passenger);

    passenger.setPersonStatus(PersonStatus.INATIVO);

    passengerRepository.save(passenger);

    DeactivatePassengerResponse response = new DeactivatePassengerResponse();
    response.setMessage("Passageiro "+ passenger.getNamePassenger() +" desativado com sucesso");

    return response;
  }
}
