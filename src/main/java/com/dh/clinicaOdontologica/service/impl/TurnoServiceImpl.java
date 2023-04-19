package com.dh.clinicaOdontologica.service.impl;
import com.dh.clinicaOdontologica.dto.TurnoDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Turno;
import com.dh.clinicaOdontologica.repository.ITurnoRepository;
import com.dh.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;
    private final Logger logger = Logger.getLogger(TurnoServiceImpl.class);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public TurnoServiceImpl(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    private void guardarTurno(TurnoDTO turnoDTO) throws ServiceException {
        Set<Turno> turnos = turnoDTO.getOdontologo().getTurnos();

        if (turnos == null){
            logger.debug("Guardando turno");
            Turno turno = mapper.convertValue(turnoDTO,Turno.class);
            turnoRepository.save(turno);

        } else {
            for (Turno turno : turnos) {
                if (turno.getFecha().equals(turnoDTO.getFecha())){
                    throw new ServiceException("El odontólogo asignado al turno ya está ocupado en esa fecha. " +
                            "Por favor intente nuevamente.");
                }
            }
            logger.debug("Creando turno...");
            guardarTurno(turnoDTO);
        }
    }

    @Override
    public void crearTurno(TurnoDTO turnoDTO) throws BadRequestException {
        if (turnoDTO.getPaciente().getId() == null || turnoDTO.getOdontologo().getId() == null){
            throw new BadRequestException("El turno que está intentando crear posee un paciente u " +
                    "odontólogo nulos.");
        } else {
            logger.debug("Creando turno...");
            guardarTurno(turnoDTO);
        }
    }

    @Override
    public TurnoDTO buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        logger.debug("Buscando turno con id: " + id);
        Turno turno = turnoRepository.findById(id).orElse(null);
        if(turno == null) {
            throw new ResourceNotFoundException("El turno con id: " + id + " no existe.");
        } else {
            return mapper.convertValue(turno, TurnoDTO.class);
        }
    }



    @Override
    public void modificarTurno(TurnoDTO turnoDTO) throws BadRequestException {
        if (turnoDTO.getId() == null){
            throw new BadRequestException("El turno que está intentando modificar no existe.");
        } else {
            logger.debug("Modificando turno.");
            guardarTurno(turnoDTO);
        }
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Optional<Turno> busqueda = turnoRepository.findById(id);
        if(busqueda.isPresent()){
            logger.debug("Eliminando el turno con id: " + id);
            turnoRepository.delete(busqueda.get());
        } else {
            throw new ResourceNotFoundException("El id N° " + id + " no existe.");
        }
    }

    @Override
    public Set<TurnoDTO> listarTodosLosTurnos() throws ServiceException {
        List<Turno> turnos = turnoRepository.findAll();

        if (turnos.isEmpty()) {
            throw new ServiceException("No hay turnos para listar.");
        } else {
            Set<TurnoDTO> turnosDTO = new HashSet<>();
            for (Turno turno : turnos) {
                turnosDTO.add(mapper.convertValue(turno, TurnoDTO.class));
            }
            logger.debug("Listando todos los turnos");
            return turnosDTO;
        }
    }

}
