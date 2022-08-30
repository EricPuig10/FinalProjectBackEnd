package com.app.finalproject.services;

import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.ICandidatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatService implements ICandidatService {

    private ICandidatRepository candidatRepository;

    public CandidatService(ICandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    @Override
    public List<Candidat> getAll(User auth) {
        List <Candidat> candidats = candidatRepository.findAll();
        List <Candidat> candidatsList = new ArrayList<>();
        candidats.forEach(Candidat -> {
            Candidat candidat = new Candidat();
            candidatsList.add(candidat);
        });
        return candidatsList;

    }


}
