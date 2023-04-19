package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.dto.DomicilioDTO;
import com.dh.clinicaOdontologica.dto.OdontologoDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;

import java.util.Set;

public interface IOdontologoService {
    void crearOdontologo(OdontologoDTO odontologoDTO) throws BadRequestException;
    OdontologoDTO buscarOdontologoPorId(Long id) throws ResourceNotFoundException;
    void modificarOdontologo(OdontologoDTO odontologoDTO) throws BadRequestException;
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
    Set<OdontologoDTO> listarTodosLosOdontologos() throws ServiceException;
    OdontologoDTO buscarOdontologoPorMatricula(String matricula) throws ResourceNotFoundException;
}
