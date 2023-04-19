package com.dh.clinicaOdontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message){
        super (message);
    }

    //ResourceNotFoundException es una excepción que se utiliza para
    // indicar que un recurso específico solicitado no se pudo encontrar.

}
