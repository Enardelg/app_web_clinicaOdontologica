package com.dh.clinicaOdontologica.model.login;

import com.dh.clinicaOdontologica.repository.security.IUserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IUserAppRepository userAppRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("pas");
        String password1 = passwordEncoder.encode("pas");

        userAppRepository.save(new UserApp("Enrique","Enardelg","admin@.com",password,UserAppRoles.ADMIN));
        userAppRepository.save(new UserApp("Armando","Arnag","armando@dh.com",password1,UserAppRoles.USER));

    }
}
