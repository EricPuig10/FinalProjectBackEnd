package com.app.finalproject.services;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IProcessStateRepository;
import com.app.finalproject.services.processS.IProcessService;
import com.app.finalproject.services.processS.ProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class ProcessServiceTest {

    @Mock
    IProcessStateRepository processStateRepository;

    @Mock
    IAuthenticationFacade authenticationFacade;

    private IProcessService processService;

    @BeforeEach
    void beforeEach() {
        this.processService = new ProcessService(processStateRepository, authenticationFacade);
    }

    @Test
    void getAllReturnsAListOfProcessState() {

        var processList = List.of(new ProcessState(), new ProcessState());
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(processStateRepository.findAll()).thenReturn(processList);

        var sut = processService.getAll(authUser);

        assertThat(sut.size(), equalTo(2));
    }
}