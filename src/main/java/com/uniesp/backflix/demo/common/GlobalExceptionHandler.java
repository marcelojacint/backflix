package com.uniesp.backflix.demo.common;

import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.service.dtos.erro.ErroCampo;
import com.uniesp.backflix.demo.service.dtos.erro.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getFieldErrors();

        List<ErroCampo> listaErros = fieldErrors
                .stream()
                .map(fie -> new ErroCampo(fie.getField(), fie.getDefaultMessage())).toList();

        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), " de validaçãerroo", listaErros);

    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handlerRegistroDuplicadoException(EntidadeNaoEncontradaException e) {
        return ErroResposta.conflito(e.getMessage());
    }
}
