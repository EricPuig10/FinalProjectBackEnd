package com.app.finalproject.auth.facade;

import com.app.finalproject.models.User;
import com.app.finalproject.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Autowired
    AuthRepository authRepository;

    public User getAuthUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authRepository.findByUsername(userName).get();
    }
}
