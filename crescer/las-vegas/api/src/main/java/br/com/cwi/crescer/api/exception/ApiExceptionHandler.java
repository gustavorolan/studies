package br.com.cwi.crescer.api.exception;

import br.com.cwi.crescer.api.controller.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handlerResponseStatusException(ResponseStatusException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getReason())
                .build();

        return new ResponseEntity(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Ocorreram erros de validação")
                .errors(extractValidationDetails(ex))
                .build();

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private List<ErrorResponse.ErrorDetailResponse> extractValidationDetails(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(e -> ErrorResponse.ErrorDetailResponse.builder()
                        .field(((FieldError) e).getField())
                        .message(e.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
