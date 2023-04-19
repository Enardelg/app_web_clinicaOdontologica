package com.dh.clinicaOdontologica.service.impl;
import com.dh.clinicaOdontologica.dto.OdontologoDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;
import com.dh.clinicaOdontologica.model.Odontologo;
import com.dh.clinicaOdontologica.repository.IOdontologoRepository;
import com.dh.clinicaOdontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class OdontologoServiceImpl implements IOdontologoService {
    @Autowired
    private IOdontologoRepository odontologoRepository;

    private final Logger logger = Logger.getLogger(OdontologoServiceImpl.class);

    @Autowired
    ObjectMapper mapper;


    @Autowired
    public OdontologoServiceImpl(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }



    @Override
    public void crearOdontologo(OdontologoDTO odontologoDTO) throws BadRequestException {
        if(odontologoDTO == null){
            throw new BadRequestException("El odontologo que se intenta crear es nulo");
        }else{
            logger.info("Creando odontologo...");
            Odontologo odontologo = mapper.convertValue(odontologoDTO,Odontologo.class);
            odontologoRepository.save(odontologo);
        }

    }

    @Override
    public OdontologoDTO buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        logger.info("Buscando odontologo con id: " + id);
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
        return mapper.convertValue(odontologo,OdontologoDTO.class);
    }   else{
            throw new ResourceNotFoundException("El odontologo con id: " + id + " no existe en la base de datos.");
        }
    }

    @Override
    public void modificarOdontologo(OdontologoDTO odontologoDTO) throws BadRequestException {
        if (odontologoDTO.getId() == null){
            throw new BadRequestException("El odontologo que se intenta modificar no existe en la base de datos");
        } else{
            logger.info("Modificando odontologo...");
            Odontologo odontologo = mapper.convertValue(odontologoDTO,Odontologo.class);
            odontologoRepository.save(odontologo);
        }
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
    if(odontologoRepository.findById(id).isEmpty()){
        throw new ResourceNotFoundException("No se puede eliminar el odontologo con id: " + id + " , ya que no existe.");
    } else{
        logger.info("Eliminando el odontologo con id: " + id);
        odontologoRepository.delete(odontologoRepository.findById(id).get());
    }
    }

    @Override
    public Set<OdontologoDTO> listarTodosLosOdontologos() throws ServiceException{
        List<Odontologo> odontologos = odontologoRepository.findAll();
        if(odontologos.isEmpty()){
            throw new ServiceException("No hay ningun odontologo para listar");
        }else{
            Set <OdontologoDTO> odontologosDto = new HashSet<>();
            for(Odontologo odontologo : odontologos){
                odontologosDto.add(mapper.convertValue(odontologo,OdontologoDTO.class));
            }
            logger.info("Listando todos los odontologos");
            return odontologosDto;
        }
    }

    @Override
    public OdontologoDTO buscarOdontologoPorMatricula(String matricula) throws ResourceNotFoundException {
        if(odontologoRepository.buscarOdontologoPorMatricula(matricula) == null){
            throw new ResourceNotFoundException("No existe ningun odontologo con matricula: " + matricula);
        }else{
            logger.info("Buscando odontologo con matricula: " + matricula);
            Odontologo odontologo = odontologoRepository.buscarOdontologoPorMatricula(matricula);
            OdontologoDTO odontologoDTO;
            odontologoDTO = mapper.convertValue(odontologo,OdontologoDTO.class);
            return odontologoDTO;
        }
    }
}
