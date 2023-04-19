package com.dh.clinicaOdontologica;

import com.dh.clinicaOdontologica.dto.OdontologoDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OdontologoServiceImplTest {
  /// crear datos afuera
    private final Logger logger = Logger.getLogger(IOdontologoService.class);
    @Autowired
    private IOdontologoService odontologoService;

    @Test
    public void crearOdontologoTest() throws BadRequestException{
        logger.debug("Creando a odontologo desde un test");
        OdontologoDTO odontologoDTO = new OdontologoDTO();

        odontologoDTO.setApellido("Enrique");
        odontologoDTO.setMatricula("DEA1248");
        odontologoDTO.setNombre("Delgado");

        odontologoService.crearOdontologo(odontologoDTO);
        Assert.assertNotNull(odontologoDTO);

    }

    @Test
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
        logger.debug("Eliminando a odontologo desde un test");
    }



}