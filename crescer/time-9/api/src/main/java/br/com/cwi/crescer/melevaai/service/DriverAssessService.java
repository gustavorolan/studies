package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.DriverAssessRequest;
import br.com.cwi.crescer.melevaai.controller.response.DriverAssessResponse;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverAssessService {
    @Autowired
    private RideFinderByIdService rideFinderByIdService;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private  Validators validators;

    public DriverAssessResponse put(DriverAssessRequest request) {
        validators.verifyIfScoreIsBetweenZeroAndFive(request.getScore());
       Ride ride = rideFinderByIdService.findByIdWithException(request.getRideId());
       Passenger passenger = ride.getPassenger();
       ride.setPassengerScore(request.getScore());
       float passengerAverage = averageCalculator(passenger);
       passenger.setScore(passengerAverage);
       rideRepository.save(ride);
       DriverAssessResponse response = new DriverAssessResponse();
       response.setResponse("Você avaliou o "+passenger.getNamePassenger()+" com a nota: "+request.getScore());
       return response;
    }

    private float averageCalculator (Passenger passenger){
        List<Ride> ridesWithoutNullValues = passenger.getRides().stream()
                .filter(rideItem -> rideItem.getPassengerScore() != null)
                .collect(Collectors.toList());

        float totalScore = ridesWithoutNullValues.stream()
                .map(rideItem-> rideItem.getPassengerScore())
                .reduce(0,(acc,ridePassengerScore)-> acc + ridePassengerScore);

        float average = totalScore/(ridesWithoutNullValues.toArray().length);

        return average;
    }

}
