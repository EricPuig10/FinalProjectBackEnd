package com.app.finalproject.mappers;

import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidatMapperTest {

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
    void mapToRes() {
        var candidat = createCandidat();
        var auth = new User();
        var sut = new CandidatMapper().mapToRes(candidat, auth);
        assertEquals(sut.getName(), candidat.getName());
        assertEquals(sut.getEmail(), candidat.getEmail());
        assertEquals(sut.getId(), candidat.getId());
    }


}