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
@Table(name = "Pacientes")
@AllArgsConstructor
@NoArgsConstructor

public class Paciente {
    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    private Long id;
    private String apellido;
    private String nombre;
    private String dni;
    @Column (name = "fecha_ingreso")
    private String fechaDeIngreso;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Domicilio> domicilios = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set <Turno> turnos = new HashSet<>();

}
