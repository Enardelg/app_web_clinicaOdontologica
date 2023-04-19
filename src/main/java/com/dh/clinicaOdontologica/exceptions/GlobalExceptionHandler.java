package com.dh.clinicaOdontologica.exceptions;


import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    //GlobalExceptionHandler es un controlador de excepciones global que
    // maneja cualquier excepción no capturada en una aplicación.

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> notFoundException(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>("No se encontró el valor en la base de datos, " +
                "por favor ingrese un valor diferente.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException ex){
        logger.error(ex.getMessage());
        return new ResponseEntity<>("Existen campos vacíos. Por favor intente nuevamente.",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> serviceException(ServiceException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
