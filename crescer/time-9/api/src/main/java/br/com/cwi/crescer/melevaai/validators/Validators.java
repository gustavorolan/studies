package br.com.cwi.crescer.melevaai.validators;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class Validators {

    public  void verifyIfMoneyInputIsBiggerThanZero(BigDecimal bigDecimal){
        if(bigDecimal.compareTo(BigDecimal.valueOf(0))==-1)
            throw new ResponseStatusException(BAD_REQUEST, "Valor deve ser maior que zero");
    }



    public  void verifyIfScoreIsBetweenZeroAndFive(float score){
        if(!(0<=score && score<=5))
            throw new ResponseStatusException(BAD_REQUEST, "Nota deve ser entre 0 e 5");
    }


}
