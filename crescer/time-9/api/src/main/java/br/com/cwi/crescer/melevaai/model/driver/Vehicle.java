package br.com.cwi.crescer.melevaai.model.driver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@ToString(of = "vehicleId")
@EqualsAndHashCode(of = "vehicleId")
@Setter
@Getter
@Entity
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long vehicleId;

  @Column(nullable = false)
  private String plate;

  @Column(nullable = false)
  private String model;

  @Column(nullable = false)
  private String color;

  @Column(nullable = false)
  private String image;

  @Enumerated(EnumType.STRING)
  private Category category;

  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  @JoinColumn(name = "driver_id")
  private Driver driver;

}
