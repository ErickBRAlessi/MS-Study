package br.com.alessi.back.productapi.advice;

import br.com.alessi.back.common.dto.ErrorDTO;
import br.com.alessi.back.common.exception.CategoryNotFoundException;
import br.com.alessi.back.common.exception.ProductNotFoundException;
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
@ControllerAdvice(basePackages = "br.com.alessi.back.productapi.controller")
public class ProductControllerAdvice {

    /*Outro erro que indiquei no capítulo 5 é quando não é informado um campo
     obrigatório na hora de salvar um novo produto. Quando isso acontece, o
     Spring retorna o erro MethodArgumentNotValidException , por isso também
     podemos adicionar um método na classe ProductControllerAdvice que
     trata esse erro. Nesse caso retornaremos uma mensagem indicando quais
     campos possuem valores inválidos.*/
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

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handleCategoryNotFound(
            CategoryNotFoundException categoryNotFoundException) {
        return ErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Category not found")
                .timestamp(new Date())
                .build();
    }

}
