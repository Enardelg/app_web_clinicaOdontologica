package com.dh.clinicaOdontologica.repository.security;
import com.dh.clinicaOdontologica.model.login.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IUserAppRepository extends JpaRepository<UserApp,Long> {

    Optional<UserApp> findByEmail(String email);
}
