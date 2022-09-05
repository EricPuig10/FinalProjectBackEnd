package com.app.finalproject.services;

import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.dtos.candidats.CandidatResToDataTable;
import com.app.finalproject.exceptions.NotFoundException;
import com.app.finalproject.mappers.CandidatMapper;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.ICandidatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatService implements ICandidatService {


    private ICandidatRepository candidatRepository;

    public CandidatService(ICandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    @Override
    public List<CandidatResToDataTable> getAll(User auth) {
        List <Candidat> candidats = candidatRepository.findAll();
        List <CandidatResToDataTable> candidatsList = new ArrayList<>();
        candidats.forEach(Candidat -> {
            CandidatResToDataTable candidat = new CandidatMapper().mapToResData(Candidat, auth);
            candidatsList.add(candidat);
        });
        return candidatsList;

    }

    @Override
    public List<CandidatRes> findByBootcampCandidats(Long id, User auth) {

        return new CandidatMapper().mapMultipleCandidatesToRes(candidatRepository.getCandidatsByBootcampId(id), auth);
    }

    @Override
    public List<CandidatRes> findByProcessCandidats(Long id, User auth) {

        return new CandidatMapper().mapMultipleCandidatesToRes(candidatRepository.getCandidatsByProcessId(id), auth);
    }

    @Override
    public CandidatRes findById(Long id, User auth) {
        Optional<Candidat> foundCandidat = candidatRepository.findById(id);
        if(foundCandidat.isEmpty()) throw new NotFoundException("Candidat Not Found", "M-404");
        CandidatRes resCandidat = new CandidatMapper().mapToRes(foundCandidat.get(), auth);
        return resCandidat;
    }
    @Override
    public List<CandidatRes> findCandidatesByBootcampId(Long id, User authUser) {
        return new CandidatMapper().mapMultipleCandidatesToRes(candidatRepository.getCandidatsByBootcampId(id), authUser);
    }



    @Override
    public Candidat create(CandidatReq candidatReq, User auth) {
        Candidat candidat = new Candidat();
        candidat.setName(candidatReq.getName());
        candidat.setLastname(candidatReq.getLastname());
        candidat.setSecondlastname(candidatReq.getSecondlastname());
        candidat.setEmail(candidatReq.getEmail());
        candidat.setBootcamp(candidatReq.getBootcamp());
        candidat.setProcessState(candidatReq.getProcessState());
        candidat.setPhone(candidatReq.getPhone());
        candidat.setAge(candidatReq.getAge());
        candidat.setNationality(candidatReq.getNationality());
        candidat.setLaboralsituation(candidatReq.getLaboralsituation());
        candidat.setGender(candidatReq.getGender());

        return candidatRepository.save(candidat);
    }

    @Override
    public CandidatRes updateACandidat(CandidatReq candidatReq, Long id, User auth){

        var candidat = candidatRepository.findById(id);

        if(candidat.isEmpty()) throw new NotFoundException("Candidat Not Found", "M-404");

        Candidat updatedCandidat = new CandidatMapper().mapRequestToCandidatToEdit(candidatReq, candidat.get());
        candidatRepository.save(updatedCandidat);
        CandidatRes candidatRes = new CandidatMapper().mapToRes(updatedCandidat, auth);
        return candidatRes;
    }

    @Override
    public CandidatRes deleteCandidat(Long id, User auth){
        Candidat candidat = this.candidatRepository.findById(id).get();
        CandidatRes resCandidat = new CandidatMapper().mapToRes(candidat, auth);
        this.candidatRepository.delete(candidat);
        return resCandidat;
    }

}
