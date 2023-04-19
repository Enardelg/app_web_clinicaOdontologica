package com.dh.clinicaOdontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ServiceException extends Exception{
    public ServiceException(String message){
        super(message);
    }

    //ServiceException es una excepción genérica que se utiliza
    // para indicar que se ha producido un error en un servicio.
}
