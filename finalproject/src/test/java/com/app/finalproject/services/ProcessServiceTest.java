package com.app.finalproject.services;

import com.app.finalproject.auth.facade.IAuthenticationFacade;
import com.app.finalproject.models.Candidat;
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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

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


    private ProcessState createProcess() {
        var authUser = new User();
        authUser.setId(1L);

        var process = new ProcessState();
        process.setId(1L);

        return process;
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


    @Test
    void findByIdShouldReturnABootcampWithSameParamId(){

        var process = this.createProcess();
        var authUser = new User();

        Mockito.when(processStateRepository.findById(any(Long.class))).thenReturn(Optional.of(process));
        var sut = processService.findById(1L, authUser);
        assertThat(sut.getName(), equalTo(process.getName()));

    }

}

