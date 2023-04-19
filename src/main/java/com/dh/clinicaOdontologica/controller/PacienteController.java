package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.dto.PacienteDTO;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.exceptions.ServiceException;
import com.dh.clinicaOdontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private IPacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDTO pacienteDTO) throws BadRequestException {
        pacienteService.crearPaciente(pacienteDTO);
        return ResponseEntity.ok("El paciente se ha creado exitosamente.");
    }

    @GetMapping("/{id}")
    public PacienteDTO buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        return pacienteService.buscarPacientePorId(id);
    }

    @GetMapping("/todos")
    public Collection<PacienteDTO> listarTodosLosPacientes() throws ServiceException {
        return pacienteService.listarTodosLosPacientes();
    }

    @PutMapping
    public ResponseEntity<?> modificarPaciente(@RequestBody PacienteDTO pacienteDTO) throws BadRequestException {
        pacienteService.modificarPaciente(pacienteDTO);
        return ResponseEntity.ok("El paciente ha sido modificado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("El paciente ha sido eliminado.");
    }

    @GetMapping("/buscarPorDni/{dni}")
    public PacienteDTO buscarPacientePorDni(@PathVariable String dni) throws ResourceNotFoundException {
        return pacienteService.buscarPacientePorDni(dni);
    }

}
