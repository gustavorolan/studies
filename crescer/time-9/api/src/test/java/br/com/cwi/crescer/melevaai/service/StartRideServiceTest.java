package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyRideStatusService;
import br.com.cwi.crescer.melevaai.util.RideFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartRideServiceTest {

  @InjectMocks
  private StartRideService service;

  @Mock
  private RideRepository rideRepository;

  @Mock
  private RideFinderByIdService rideFinderByIdService;

  @Mock
  private VerifyRideStatusService verifyRideStatusService;

  @Mock
  private DateTimeService dateTimeService;

  @Mock
  private StartRideUtilsService startRideUtilsService;

  @Captor
  private ArgumentCaptor<Ride> rideArgumentCaptor;

  @Test
  @DisplayName("Deve começar corrida com sucesso")
  public void deveComecarCorridaComSucesso() {

    Long rideId = 1L;

    Ride ride = RideFactory.get();

    when(rideFinderByIdService.findByIdWithException(rideId))
        .thenReturn(ride);

    service.startRide(rideId);

    verify(rideRepository).save(rideArgumentCaptor.capture());
    Ride rideUsedInTest = rideArgumentCaptor.getValue();

    assertEquals(RideStatus.INICIADA, rideUsedInTest.getStatus());
  }

}