package br.com.cwi.crescer.melevaai.controller.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class MoneyWithdrawRequest {
    @NotNull
    private BigDecimal MoneyToDraw;

    @NotNull
    private Long DriverID;
}
