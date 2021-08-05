package br.com.alessi.back.userapi.advice;

import br.com.alessi.back.common.dto.ErrorDTO;
import br.com.alessi.back.common.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;

/*
@ControllerAdvice indica que ela deve verificar as exceções
retornadas em todos os controllers. O valor HttpStatus.NOT_FOUND passado
na anotação @ResponseStatus indica que deve ser retornado o erro 404
como status da resposta. A exceção UserNotFoundException.class na
anotação @ExceptionHandler indica que esse método deve capturar esse
tipo de exceções. Finalmente, a anotação @ResponseBody define que o
retorno desse método será retornado no corpo da resposta.
 */
@ControllerAdvice(basePackages = "br.com.alessi.back.userapi.controller")
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFound(
            UserNotFoundException userNotFoundException) {
        return ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("User not found")
                .timestamp(new Date())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO processValidationError(
            MethodArgumentNotValidException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder sb =
                new StringBuilder("Valor inválido para o(s) campo(s):");
        for (FieldError fieldError : fieldErrors) {
            sb.append(" ");
            sb.append(fieldError.getField());
        }
        errorDTO.setMessage(sb.toString());
        errorDTO.setTimestamp(new Date());
        return errorDTO;
    }

}
