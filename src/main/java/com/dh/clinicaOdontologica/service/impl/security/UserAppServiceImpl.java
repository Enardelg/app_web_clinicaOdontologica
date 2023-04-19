package com.dh.clinicaOdontologica.service.impl.security;

import com.dh.clinicaOdontologica.repository.security.IUserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAppServiceImpl implements UserDetailsService {

    private final IUserAppRepository userAppRepository;

    @Autowired
    public UserAppServiceImpl(IUserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAppRepository.findByEmail(email).orElseThrow((()-> new UsernameNotFoundException("User email not found")));
    }
}
