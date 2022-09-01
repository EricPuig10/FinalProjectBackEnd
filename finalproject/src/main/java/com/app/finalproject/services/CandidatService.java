package com.app.finalproject.services;

import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.mappers.CandidatMapper;
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
    public List<CandidatRes> getAll(User auth) {
        List <Candidat> candidats = candidatRepository.findAll();
        List <CandidatRes> candidatsList = new ArrayList<>();
        candidats.forEach(Candidat -> {
            CandidatRes candidat = new CandidatMapper().mapToRes(Candidat, auth);
            candidatsList.add(candidat);
        });
        return candidatsList;

    }

    @Override
    public List<CandidatRes> findByBootcampCandidats(Long id, User auth) {

        return new CandidatMapper().mapMultipleMomentsToRes(candidatRepository.getCandidatsByBootcampId(id), auth);
    }

    @Override
    public List<CandidatRes> findByProcessCandidats(Long id, User auth) {

        return new CandidatMapper().mapMultipleMomentsToRes(candidatRepository.getCandidatsByProcessId(id), auth);
    }



}
