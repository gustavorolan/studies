package br.com.cwi.crescer.melevaai.service.verifiers;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class VerifyIfDrawIsBiggerThanBalanceService {
    public  void verifyIfDrawIsBiggerThanBalance(BigDecimal bigDecimalA, BigDecimal bigDecimalB){
        if(bigDecimalA.compareTo(bigDecimalB)==1)
            throw new ResponseStatusException(BAD_REQUEST, "Voce não pode retirar mais dinheiro do que você tem");
    }
}
