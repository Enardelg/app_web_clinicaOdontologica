package com.dh.clinicaOdontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Builder
@Getter
@Setter
@Entity
@Table(name = "Odontologos")
@AllArgsConstructor
@NoArgsConstructor
public class Odontologo {
    @Id
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
    private Long id;
    private String apellido;
    private String nombre;
    @Column (name = "numero_matricula")
    private String matricula;
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY)
    private Set<Paciente> pacientes = new HashSet<>();

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnos;

}
