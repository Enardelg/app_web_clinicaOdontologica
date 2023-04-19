package com.dh.clinicaOdontologica;

import com.dh.clinicaOdontologica.dto.OdontologoDTO;
import com.dh.clinicaOdontologica.dto.PacienteDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.repository.IPacienteRepository;
import com.dh.clinicaOdontologica.service.IOdontologoService;
import com.dh.clinicaOdontologica.service.IPacienteService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceImplTest {

    private final Logger logger = Logger.getLogger(IOdontologoService.class);
    @Autowired
    private IPacienteService pacienteService;
    @Test
    void crearPacienteTest() throws BadRequestException {
        logger.debug("Creando a paciente desde un test");
        PacienteDTO pacienteDTO = new PacienteDTO();

        pacienteDTO.setApellido("Najera");
        pacienteDTO.setNombre("Armando");
        pacienteDTO.setDni("36701940");
        pacienteDTO.setFechaDeIngreso("2023-03-30");

        pacienteService.crearPaciente(pacienteDTO);
        Assert.assertNotNull(pacienteDTO);
    }

    @Test
    void eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(1L);
        logger.debug("Eliminando a paciente desde un test");
    }
}


