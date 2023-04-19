package com.dh.clinicaOdontologica.service.impl;
import com.dh.clinicaOdontologica.dto.DomicilioDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;
import com.dh.clinicaOdontologica.model.Domicilio;
import com.dh.clinicaOdontologica.repository.IDomicilioRepository;
import com.dh.clinicaOdontologica.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DomicilioServiceImpl  implements IDomicilioService {
    @Autowired
    private IDomicilioRepository domicilioRepository;

    private final Logger logger = Logger.getLogger(OdontologoServiceImpl.class);

    @Autowired
    ObjectMapper mapper;

    public DomicilioServiceImpl(IDomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }


    @Override
    public void crearDomicilio(DomicilioDTO domicilioDTO) throws BadRequestException {
        if(domicilioDTO == null){
            throw new BadRequestException("El domicilio que se intenta crear es nulo");
        }else{
            logger.info("Creando domicilio...");
            Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
            domicilioRepository.save(domicilio);
        }

    }

    @Override
    public DomicilioDTO buscarDomicilioPorId(Long id) throws ResourceNotFoundException {
        logger.info("Buscando domicilio con id: " + id);
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        if(domicilio.isPresent()){
            return mapper.convertValue(domicilio,DomicilioDTO.class);
        }   else{
            throw new ResourceNotFoundException("El domicilio con id: " + id + " no existe en la base de datos.");
        }
    }

    @Override
    public void modificarDomicilio(DomicilioDTO domicilioDTO) throws BadRequestException {
        if (domicilioDTO.getId() == null){
            throw new BadRequestException("El domicilio que se intenta modificar no existe en la base de datos");
        } else{
            logger.info("Modificando domicilio...");
            Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
            domicilioRepository.save(domicilio);
        }
    }

    @Override
    public void eliminarDomicilio(Long id) throws ResourceNotFoundException {
        if(domicilioRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No se puede eliminar el domicilio con id: " + id + " , ya que no existe.");
        } else{
            logger.info("Eliminando domicilio con id: " + id);
            domicilioRepository.delete(domicilioRepository.findById(id).get());
        }
    }

    @Override
    public Set<DomicilioDTO> listarTodosLosDomicilios() throws ServiceException {
        List<Domicilio> domicilios = domicilioRepository.findAll();
        if(domicilios.isEmpty()){
            throw new ServiceException("No hay ningun domicilio para listar");
        }else{
            Set <DomicilioDTO> domicilioDTO = new HashSet<>();
            for(Domicilio domicilio : domicilios){
                domicilioDTO.add(mapper.convertValue(domicilio,DomicilioDTO.class));
            }
            logger.info("Listando todos los domicilios...");
            return domicilioDTO;
        }
    }

}
