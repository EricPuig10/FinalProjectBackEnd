package com.app.finalproject.services;

import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.exceptions.NotFoundException;
import com.app.finalproject.mappers.CandidatMapper;
import com.app.finalproject.models.*;
import com.app.finalproject.repositories.IBootcampRepository;
import com.app.finalproject.repositories.ICandidatRepository;
import com.app.finalproject.repositories.IProcessStateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CandidatServiceTest {

    @Mock
    ICandidatRepository candidatRepository;
    @Mock
    IBootcampRepository bootcampRepository;
    @Mock
    IProcessStateRepository processStateRepository;

    ICandidatService candidatService;
    @BeforeEach
    void beforeEach() {
        this.candidatService = new CandidatService(candidatRepository, bootcampRepository, processStateRepository);
    }


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


    @Test
    void createSaveACandidat() {

        Date now = new Date();
        CandidatReq candidatReq = new CandidatReq( "Eric", "Puigvendrello", "Soldado", "puigvendrelloetona@gmail.com", 123456789L, 21L, "Bachillerato", "Invisibilidad" , "Calle 13", "B1" , "Full Stack", "Instagram", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500", "Masculino", "Cataluña", "Desempleado", "Osona", "Registradx", "https://media-exp1.licdn.com/dms/image/C4D03AQG17WUfd78sgA/profile-displayphoto-shrink_800_800/0/1587477510501?e=1668038400&v=beta&t=CDPVd3nDIuH06ntqP_TcBYJJYfo-SmgegCV6zvB1CHI", "Tona", "NIF", "11111111W", now, "https://www.codecademy.com/profiles/EricPuig", "https://www.sololearn.com/profile/24969081", true);

        var candidat = createCandidat();
        var authUser = new User();

        var bootcamp = new Bootcamp("Osona");
        var process = new ProcessState("First");
        Mockito.when(bootcampRepository.findByBootcampName(any(String.class))).thenReturn(Optional.of(bootcamp));
        Mockito.when(processStateRepository.findByName(any(String.class))).thenReturn(Optional.of(process));
        Mockito.when(candidatRepository.save(any(Candidat.class))).thenReturn(candidat) ;

        var sut = candidatService.create(candidatReq, authUser);

        assertThat(sut.getName(), equalTo(candidat.getName()));
    }

    @Test
    void findByIdShouldReturnACandidatWithSameParamId() {

        var candidat = this.createCandidat();
        var authUser = new User();
        authUser.setId(1L);

        Mockito.when(candidatRepository.findById(any(Long.class))).thenReturn(Optional.of(candidat));

        var sut = candidatService.findById(1L, authUser);

        assertThat(sut.getName(), equalTo(candidat.getName()));
    }

    @Test
    void deleteShouldReturnDeletedCandidat() throws IOException {
        Long id = 1L;
        var authUser = new User();
        authUser.setId(1L);
        Candidat candidat = createCandidat();
        Mockito.when(candidatRepository.findById(any(Long.class))).thenReturn(Optional.of(candidat));
        var sut = candidatService.deleteCandidat(id, authUser) ;
        assertThat(sut.getName(), equalTo(candidat.getName()));
    }

    @Test
    void updateShouldUpdateCandidatFromReq() throws IOException {
        var req = new CandidatReq( "Eric", "Puigvendrello", "Soldado", "puigvendrelloetona@gmail.com", 123456789L, 21L, "Bachillerato", "Invisibilidad" , "Calle 13", "B1" , "Full Stack", "Instagram", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500", "Masculino", "Cataluña", "Desempleado", "Osona", "Registradx", "https://media-exp1.licdn.com/dms/image/C4D03AQG17WUfd78sgA/profile-displayphoto-shrink_800_800/0/1587477510501?e=1668038400&v=beta&t=CDPVd3nDIuH06ntqP_TcBYJJYfo-SmgegCV6zvB1CHI", "Tona", "NIF", "11111111W", new Date(), "https://www.codecademy.com/profiles/EricPuig", "https://www.sololearn.com/profile/24969081", true);
        Long id = 1L;

        var user = new User();
        user.setId(1L);

        Candidat candidat = this.createCandidat();

        var bootcamp = new Bootcamp("Osona");
        var process = new ProcessState("First");
        Mockito.when(bootcampRepository.findByBootcampName(any(String.class))).thenReturn(Optional.of(bootcamp));
        Mockito.when(processStateRepository.findByName(any(String.class))).thenReturn(Optional.of(process));

        Mockito.when(candidatRepository.findById(any(Long.class))).thenReturn(Optional.of(candidat));
        Mockito.when(candidatRepository.save(any(Candidat.class))).thenReturn(candidat);

        var sut = candidatService.updateACandidat(req, id, user);
        assertThat(sut.getName(), equalTo(req.getName()));
        assertThat(sut.getLastname(), equalTo(req.getLastname()));

    }

    @Test
    void updateCandidatReturnsNotFoundExceotionIfDontExistCandidat() {

        var req = new CandidatReq("Eric", "Puigvendrello", "Soldado", "puigvendrelloetona@gmail.com", 123456789L, 21L, "Bachillerato", "Invisibilidad" , "Calle 13", "B1" , "Full Stack", "Instagram", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500", "Masculino", "Cataluña", "Desempleado", "Osona", "Registradx", "https://media-exp1.licdn.com/dms/image/C4D03AQG17WUfd78sgA/profile-displayphoto-shrink_800_800/0/1587477510501?e=1668038400&v=beta&t=CDPVd3nDIuH06ntqP_TcBYJJYfo-SmgegCV6zvB1CHI", "Tona", "NIF", "11111111W", new Date(), "https://www.codecademy.com/profiles/EricPuig", "https://www.sololearn.com/profile/24969081", true);
        var user = new User();
        user.setId(1L);
        Exception ex = assertThrows(NotFoundException.class, ()->{
            candidatService.updateACandidat(req, 1L, user);
        });
        var resmsg = "Candidat Not Found";
        var sut = ex.getMessage();
        assertTrue(sut.equals(resmsg));


    }


}
