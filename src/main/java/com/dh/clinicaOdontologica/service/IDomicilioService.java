package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.dto.DomicilioDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;

import java.util.Set;

public interface IDomicilioService {

    void crearDomicilio (DomicilioDTO domicilioDTO) throws BadRequestException;
    DomicilioDTO buscarDomicilioPorId(Long id) throws ResourceNotFoundException;
    void modificarDomicilio(DomicilioDTO domicilioDTO) throws BadRequestException;
    void eliminarDomicilio(Long id) throws ResourceNotFoundException;
    Set<DomicilioDTO> listarTodosLosDomicilios() throws ServiceException;
}
