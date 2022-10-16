package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.PassengerAssessRequest;
import br.com.cwi.crescer.melevaai.controller.response.PassengerAssessResponse;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerAssessService {
    @Autowired
    private RideFinderByIdService rideFinderByIdService;
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private  Validators validators;

    public PassengerAssessResponse put(PassengerAssessRequest request) {
       validators.verifyIfScoreIsBetweenZeroAndFive(request.getScore());
       Ride ride = rideFinderByIdService.findByIdWithException(request.getRideId());
       Driver driver = ride.getDriver();
       float driverScore = averageCalculator(driver);
       driver.setScore(driverScore);
       ride.setDriverScore(request.getScore());
       rideRepository.save(ride);
       PassengerAssessResponse response = new PassengerAssessResponse();
       response.setResponse("Você avaliou o "+driver.getNameDriver()+" com a nota: "+request.getScore());
       return response;
    }
    private float averageCalculator (Driver driver ){
        List<Ride> ridesWithoutNullValues = driver.getRides().stream()
                .filter(rideItem -> rideItem.getPassengerScore() != null)
                .collect(Collectors.toList());

        float totalScore = ridesWithoutNullValues.stream()
                .map(rideItem-> rideItem.getPassengerScore())
                .reduce(0,(acc,ridePassengerScore)-> acc + ridePassengerScore);

        float average = totalScore/(ridesWithoutNullValues.toArray().length);

        return average;
    }
}
