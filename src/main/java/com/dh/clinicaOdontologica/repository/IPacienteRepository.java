package com.dh.clinicaOdontologica.repository;
import com.dh.clinicaOdontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
    @Query("SELECT p FROM Paciente p WHERE p.dni = ?1")
    Paciente buscarPacientePorDni(String dni);
}
