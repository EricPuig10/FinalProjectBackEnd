package com.app.finalproject.services;

import com.app.finalproject.auth.facade.AuthenticationFacade;
import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IBootcampRepository;
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



    @Test
    void getAllReturnsListOfBootcampRes() {

    }
}