package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.dto.TurnoDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ITurnoService turnoService;

    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody TurnoDTO turnoDTO) throws BadRequestException {
        turnoService.crearTurno(turnoDTO);
        return ResponseEntity.ok("El turno ha sido creado con éxito.");
    }

    @GetMapping("/{id}")
    public TurnoDTO buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoService.buscarTurnoPorId(id);
    }

    @GetMapping
    public Collection<TurnoDTO> listarTodosLosTurnos(){
        return turnoService.listarTodosLosTurnos();
    }

    @PutMapping
    public ResponseEntity<?> modificarTurno(@RequestBody TurnoDTO turnoDTO) throws BadRequestException {
        turnoService.modificarTurno(turnoDTO);
        return ResponseEntity.ok("El turno ha sido modificado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("El turno ha sido eliminado.");
    }
}