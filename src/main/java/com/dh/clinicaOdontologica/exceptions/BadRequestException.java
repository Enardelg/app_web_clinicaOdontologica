package com.dh.clinicaOdontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{
    public BadRequestException (String message){
        super (message);
    }
    //La excepción BadRequestException es un tipo de error que indica que la
    // solicitud enviada al servidor no es válida o está mal formada.
}
