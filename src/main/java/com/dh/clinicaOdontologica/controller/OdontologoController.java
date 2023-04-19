package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.dto.OdontologoDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;
import com.dh.clinicaOdontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping ("/odontologos")
public class OdontologoController {
    @Autowired
    private IOdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<?> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws BadRequestException {
        odontologoService.crearOdontologo(odontologoDTO);
        return ResponseEntity.ok("El odontólogo ha sido creado exitosamente.");
    }

    @GetMapping("/{id}")
    public OdontologoDTO buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologoPorId(id);
    }

    @GetMapping("/todos")
    public Collection<OdontologoDTO> listarTodosLosOdontologos() throws ServiceException {
        return odontologoService.listarTodosLosOdontologos();
    }

    @PutMapping
    public ResponseEntity<?> modificarOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws BadRequestException {
        odontologoService.modificarOdontologo(odontologoDTO);
        return ResponseEntity.ok("El odontólogo ha sido modificado.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("El odontólogo ha sido eliminado");
    }

    @GetMapping("/buscarPorMatricula/{matricula}")
    public OdontologoDTO buscarOdontologoPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologoPorMatricula(matricula);
    }

}
