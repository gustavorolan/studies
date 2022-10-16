package br.com.cwi.crescer.melevaai.model.driver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@ToString(of = "driverLicenseId")
@EqualsAndHashCode(of = "driverLicenseId")
@Setter
@Getter
@Entity
public class DriverLicense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long driverLicenseId;

  @Column(nullable = false)
  private String numberLicense;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(nullable = false)
  private LocalDate expireDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  @JoinColumn(name = "driver_id")
  private Driver driver;

}
