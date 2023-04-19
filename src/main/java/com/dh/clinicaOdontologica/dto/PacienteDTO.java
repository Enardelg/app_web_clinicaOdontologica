package com.dh.clinicaOdontologica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {
    private Long id;
    private String apellido;
    private String nombre;
    private String dni;
    private String fechaDeIngreso;
}
