package br.com.cwi.crescer.melevaai.model.driver;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "driverId")
@EqualsAndHashCode(of = "driverId")
@Setter
@Getter
@Builder
@Entity
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long driverId;

  @Column(nullable = false)
  private String nameDriver;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false)
  private String cpf;

  @OneToOne(mappedBy = "driver")
  private DriverLicense driverLicense;

  @OneToOne(mappedBy = "driver")
  private Vehicle vehicle;

  @Column(nullable = false)
  private BigDecimal balance;

  @Column(nullable = false)
  private float score;

  @Enumerated(EnumType.STRING)
  private PersonStatus personStatus;

  @OneToMany(mappedBy = "driver")
  @JsonIgnore
  private List<Ride> rides;

  @Enumerated(EnumType.STRING)
  private DriverStatusRide driverStatusRide;

  private Long lastRideId;

}
