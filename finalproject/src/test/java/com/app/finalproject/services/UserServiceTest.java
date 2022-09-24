package com.app.finalproject.services;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IUserRepository;
import com.app.finalproject.services.userS.IUserService;
import com.app.finalproject.services.userS.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    IUserRepository userRepository;
    @Mock
    IAuthenticationFacade authenticationFacade;

    private IUserService userService;

    @Test
    void getAllReturnsAListOfUsers() {
        this.userService = new UserService(userRepository, authenticationFacade);
        var authUser = new User();
        var userList = List.of(new User(), new User());

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        var sut = userService.getAll();

        assertThat(sut.size(), equalTo(2));
//        TEST FAILING assertThat(sut.size(), equalTo(3));
    }

    @Test
    void getByIdShouldReturnAUserWithSameParamId() {
        this.userService = new UserService(userRepository, authenticationFacade);
        var authUser = new User();

        Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(authUser));

        var sut = userService.getById(1L, authUser);

        assertThat(sut, equalTo(authUser));
    }
}