package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.dto.DomicilioDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;
import com.dh.clinicaOdontologica.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    @Autowired
    private IDomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<?> crearDomicilio(@RequestBody DomicilioDTO domicilioDTO) throws BadRequestException {
        domicilioService.crearDomicilio(domicilioDTO);
        return ResponseEntity.ok("El domicilio ha sido creado exitosamente.");
    }

    @GetMapping("/{id}")
    public DomicilioDTO buscarDomiciliooPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return domicilioService.buscarDomicilioPorId(id);
    }

    @GetMapping
    public Collection<DomicilioDTO> listarTodosLosOdontologos() throws ServiceException {
        return domicilioService.listarTodosLosDomicilios();
    }

    @PutMapping
    public ResponseEntity<?> modificarDomicilio(@RequestBody DomicilioDTO domicilioDTO) throws BadRequestException {
        domicilioService.modificarDomicilio(domicilioDTO);
        return ResponseEntity.ok("El domicilio ha sido modificado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDomicilioPorId(@PathVariable Long id) throws ResourceNotFoundException {
        domicilioService.eliminarDomicilio(id);
        return ResponseEntity.ok("El domicilio ha sido eliminado");
    }


}
