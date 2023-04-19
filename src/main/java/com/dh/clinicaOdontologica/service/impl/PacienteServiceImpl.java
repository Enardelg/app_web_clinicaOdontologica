package com.dh.clinicaOdontologica.service.impl;
import com.dh.clinicaOdontologica.dto.PacienteDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;
import com.dh.clinicaOdontologica.model.Paciente;
import com.dh.clinicaOdontologica.repository.IPacienteRepository;
import com.dh.clinicaOdontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteServiceImpl implements IPacienteService {
    @Autowired
    private IPacienteRepository pacienteRepository;

    private final Logger logger = Logger.getLogger(OdontologoServiceImpl.class);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public void crearPaciente(PacienteDTO pacienteDTO) throws BadRequestException {
        if(pacienteDTO == null){
            throw new BadRequestException("El paciente que se intenta crear es nulo");
        }else{
            logger.info("Creando paciente...");
            Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
            pacienteRepository.save(paciente);
        }

    }

    @Override
    public PacienteDTO buscarPacientePorId(Long id) throws ResourceNotFoundException {
        logger.info("Buscando paciente con id: " + id);
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            return mapper.convertValue(paciente,PacienteDTO.class);
        }   else{
            throw new ResourceNotFoundException("El paciente con id: " + id + " no existe en la base de datos.");
        }
    }

    @Override
    public void modificarPaciente(PacienteDTO pacienteDTO) throws BadRequestException {
        if (pacienteDTO.getId() == null){
            throw new BadRequestException("El paciente que se intenta modificar no existe en la base de datos");
        } else{
            logger.info("Modificando paciente...");
            Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
            pacienteRepository.save(paciente);
        }
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(pacienteRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No se puede eliminar el paciente con id: " + id + " , ya que no existe.");
        } else{
            logger.info("Eliminando el paciente con id: " + id);
            pacienteRepository.delete(pacienteRepository.findById(id).get());
        }
    }

    @Override
    public Set<PacienteDTO> listarTodosLosPacientes() throws ServiceException {
        List<Paciente> pacientes = pacienteRepository.findAll();
        if(pacientes.isEmpty()){
            throw new ServiceException("No hay ningun paciente para listar");
        }else{
            Set <PacienteDTO> pacienteDTO = new HashSet<>();
            for(Paciente paciente : pacientes){
                pacienteDTO.add(mapper.convertValue(paciente,PacienteDTO.class));
            }
            logger.info("Listando todos los pacientes");
            return pacienteDTO;
        }
    }

    @Override
    public PacienteDTO buscarPacientePorDni(String dni) throws ResourceNotFoundException {
        if (pacienteRepository.buscarPacientePorDni(dni) == null){
            throw new ResourceNotFoundException("No existe un paciente con dni: " + dni);
        } else {
            Paciente paciente = pacienteRepository.buscarPacientePorDni(dni);
            PacienteDTO pacienteDTO;
            pacienteDTO = mapper.convertValue(paciente,PacienteDTO.class);
            return pacienteDTO;
        }
    }
}

