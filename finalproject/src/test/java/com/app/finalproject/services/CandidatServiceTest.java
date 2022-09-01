package com.app.finalproject.services;

import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.mappers.CandidatMapper;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.ICandidatRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CandidatServiceTest {

    @Mock
    ICandidatRepository candidatRepository;


    public Candidat createCandidat(){
        var bootcamp = new Bootcamp();
        bootcamp.setId(1L);
        var process = new ProcessState();
        process.setId(1L);
        var candidat = new Candidat();
        candidat.setBootcamp(bootcamp);
        candidat.setId(10L);
        candidat.setName("eric");
        candidat.setEmail("eric@gmail.com");
        candidat.setProcessState(process);
        return candidat;
    }

    @Test
    void getAllReturnsListOfCandidatsRes() {
        var candidatService = new CandidatService(candidatRepository);
        var bootcamp = new Bootcamp();
        bootcamp.setId(1L);
        var user = new User();
        user.setId(1L);
        var candidatsList = List.of(new Candidat(), new Candidat());
        candidatsList.forEach(Candidat -> Candidat.setBootcamp(bootcamp));
        Mockito.when(candidatRepository.findAll()).thenReturn(candidatsList);

        var sut = candidatService.getAll(user);

        assertThat (sut.size(), equalTo(2));
    }

    @Test
    void findByBootcampCandidatsReturnsAListOfCandidatsRes() {
        var candidatService = new CandidatService(candidatRepository);
        Candidat candidat = this.createCandidat();
        var user = new User();
        user.setId(1L);
        CandidatRes res = new CandidatMapper().mapToRes(candidat, user);
        var filtered = List.of(candidat);
        var foundCandidats = List.of(res);
        Mockito.when(candidatRepository.getCandidatsByBootcampId(any(Long.class))).thenReturn(filtered);
        var sut = candidatService.findByBootcampCandidats(1L, user);
        assertThat(sut, equalTo(foundCandidats));
    }

    @Test
    void findByProcessCandidatsReturnsAListOfCandidatsRes() {

        var candidatService = new CandidatService(candidatRepository);
        Candidat candidat = this.createCandidat();
        var user = new User();
        user.setId(1L);
        CandidatRes res = new CandidatMapper().mapToRes(candidat, user);
        var filtered = List.of(candidat);
        var foundCandidats = List.of(res);
        Mockito.when(candidatRepository.getCandidatsByProcessId(any(Long.class))).thenReturn(filtered);
        var sut = candidatService.findByProcessCandidats(1L, user);
        assertThat(sut, equalTo(foundCandidats));
    }
}