package br.com.cwi.crescer.melevaai.model.passenger;

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
@ToString(of = "passengerId")
@EqualsAndHashCode(of = "passengerId")
@Setter
@Getter
@Builder
@Entity
public class Passenger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long passengerId;

  @Column(nullable = false)
  private String namePassenger;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false)
  private String cpf;

  @Column(nullable = false)
  private BigDecimal balance;

  @Column(nullable = false)
  private float score;

  @Enumerated(EnumType.STRING)
  private PersonStatus personStatus;

  @OneToMany(mappedBy = "passenger")
  @JsonIgnore
  private List<Ride> rides;
}
