package br.com.cwi.crescer.melevaai.controller.response;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PassengerResponse {

    private Long passengerId;

    private String namePassenger;

    private LocalDate birthDate;

    private String cpf;

    private BigDecimal balance;

    private float score;

    private PersonStatus personStatus;

    private List<Ride> rides;
}
