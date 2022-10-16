package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.FinishRideResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPassangerBalanceService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyRideStatusService;
import br.com.cwi.crescer.melevaai.util.RideFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinishRideServiceTest {

  @InjectMocks
  private FinishRideService service;

  @Mock
  private RideRepository rideRepository;

  @Mock
  private RideFinderByIdService rideFinderByIdService;

  @Mock
  private VerifyRideStatusService verifyRideStatusService;

  @Mock
  private VerifyPassangerBalanceService verifyPassangerBalanceService;

  @Mock
  private FinishRideUtilsService finishRideUtilsService;

  @Mock
  private DateTimeService dateTimeService;

  @Captor
  private ArgumentCaptor<Ride> rideArgumentCaptor;

  @Test
  @DisplayName("Must finish race successfully")
  public void mustFinishRaceSuccessfully() {
    Long rideId = 1L;

    Ride ride = RideFactory.get();

    BigDecimal totalPrice = new BigDecimal("10");

    LocalDateTime dateToBeUsed = LocalDateTime.now();

    int timeInSeconds = 0;

    BigDecimal expectedPassengerNewBalance = ride.getPassenger()
        .getBalance().subtract(totalPrice);

    BigDecimal expectedDriverNewBalance = ride.getDriver()
        .getBalance().add(totalPrice);

    when(rideFinderByIdService.findByIdWithException(rideId))
        .thenReturn(ride);

    when(dateTimeService.dateAndTimeNow())
        .thenReturn(dateToBeUsed);

    when(finishRideUtilsService.getRidePrice(0))
        .thenReturn(totalPrice);

    FinishRideResponse response = service.finishRide(rideId);

    verify(rideRepository).save(rideArgumentCaptor.capture());

    Ride rideUsedInTest = rideArgumentCaptor.getValue();

    assertEquals(RideStatus.FINALIZADA, rideUsedInTest.getStatus());
    assertEquals(dateToBeUsed, ride.getFinishTime());

    assertEquals(PersonStatus.LIVRE, ride.getPassenger().getPersonStatus());
    assertEquals(expectedPassengerNewBalance, ride.getPassenger().getBalance());

    assertEquals(PersonStatus.LIVRE, ride.getDriver().getPersonStatus());
    assertEquals(expectedDriverNewBalance, ride.getDriver().getBalance());

    assertEquals(totalPrice, response.getTotalPrice());
    assertEquals(timeInSeconds, response.getTotalTimeInSeconds());

  }

}