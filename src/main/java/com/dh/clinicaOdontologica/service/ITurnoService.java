package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.dto.PacienteDTO;
import com.dh.clinicaOdontologica.dto.TurnoDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface ITurnoService {
    void crearTurno (TurnoDTO turnoDTO) throws BadRequestException;
    TurnoDTO buscarTurnoPorId(Long id) throws ResourceNotFoundException;
    void modificarTurno(TurnoDTO turnoDTO) throws BadRequestException;
    void eliminarTurno(Long id) throws ResourceNotFoundException;
    Set<TurnoDTO> listarTodosLosTurnos();
}
