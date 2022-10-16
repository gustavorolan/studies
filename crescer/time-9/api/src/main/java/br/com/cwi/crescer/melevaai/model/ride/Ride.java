package br.com.cwi.crescer.melevaai.model.ride;

import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Builder
@Entity
public class Ride  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rideId;

  @Column(nullable = false)
  private Integer startX;

  @Column(nullable = false)
  private Integer startY;

  @Column(nullable = false)
  private Integer endX;

  @Column(nullable = false)
  private Integer endY;

  @Column(nullable = false)
  private LocalDateTime startTime;

  private LocalDateTime finishTime;

  @Enumerated(EnumType.STRING)
  private RideStatus status;

  private Integer driverScore;

  private Integer passengerScore;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "driver_id")
  private Driver driver;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "passenger_id")
  private Passenger passenger;
}
