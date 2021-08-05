package br.com.alessi.back.productapi.advice;

import br.com.alessi.back.common.dto.ErrorDTO;
import br.com.alessi.back.common.exception.ProductNotFoundException;
import br.com.alessi.back.common.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

/*
@ControllerAdvice indica que ela deve verificar as exceções
retornadas em todos os controllers. O valor HttpStatus.NOT_FOUND passado
na anotação @ResponseStatus indica que deve ser retornado o erro 404
como status da resposta. A exceção UserNotFoundException.class na
anotação @ExceptionHandler indica que esse método deve capturar esse
tipo de exceções. Finalmente, a anotação @ResponseBody define que o
retorno desse método será retornado no corpo da resposta.
 */
@ControllerAdvice(basePackages = "br.com.alessi.back.productapi.controller")
public class ShoppingControllerAdvice {

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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleProductNotFound(
            ProductNotFoundException productNotFoundException) {
        return ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Product not found")
                .timestamp(new Date())
                .build();
    }

}
