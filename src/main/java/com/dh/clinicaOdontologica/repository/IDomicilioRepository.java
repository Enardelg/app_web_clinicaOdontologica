package com.dh.clinicaOdontologica.repository;
import com.dh.clinicaOdontologica.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository <Domicilio, Long> {
}
