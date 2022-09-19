package com.app.finalproject.services;

import com.app.finalproject.auth.facade.AuthenticationFacade;
import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IBootcampRepository;
import com.app.finalproject.repositories.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BootcampServiceTest {

    @Mock
    IBootcampRepository bootcampRepository;
    @Mock
    IAuthenticationFacade authenticationFacade;

    @Mock
    ICategoryRepository categoryRepository;

    private IBootcampService bootcampService;

    @BeforeEach
    void beforeEach(){
        this.bootcampService = new BootcampService(bootcampRepository, authenticationFacade, categoryRepository);

    }


    @Test
    void getAllReturnListOfBootcamp(){
        var bootcampService = new BootcampService(bootcampRepository, authenticationFacade, categoryRepository);
        var bootcampList = List.of(new Bootcamp(), new Bootcamp());
        var authUser = new User ();
        authUser.setId(1L);

        Mockito.when(bootcampRepository.findAll()).thenReturn(bootcampList);

        var sut = bootcampService.getAll();
        assertThat(sut.size(), equalTo(2));

    }








}