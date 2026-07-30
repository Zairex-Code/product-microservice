package com.nttdata.product.exception;

import com.nttdata.product.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> noEncontrado(ProductoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("PRODUCTO_NO_ENCONTRADO", "El producto no fue encontrado", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> datosInvalidos(MethodArgumentNotValidException ex) {
        String detalle = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest()
                .body(error("DATOS_INVALIDOS", "Los datos enviados no son válidos", detalle));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> interno(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error("ERROR_INTERNO", "Ocurrió un error inesperado", ex.getMessage()));
    }

    private ErrorResponse error(String codigo, String mensaje, String detalle) {
        ErrorResponse e = new ErrorResponse();
        e.setCodigo(codigo);
        e.setMensaje(mensaje);
        e.setDetalle(detalle);
        return e;
    }
}
