package com.dh.clinicaOdontologica.dto;

import com.dh.clinicaOdontologica.model.Odontologo;
import com.dh.clinicaOdontologica.model.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnoDTO {
    private Long id;
    private String fecha;
    private String hora;
    private Odontologo odontologo;
    private Paciente paciente;
}
