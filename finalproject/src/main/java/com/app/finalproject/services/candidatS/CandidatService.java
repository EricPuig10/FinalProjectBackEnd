package com.app.finalproject.services.candidatS;

import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.exceptions.NotFoundException;
import com.app.finalproject.mappers.CandidatMapper;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;
import com.app.finalproject.repositories.IBootcampRepository;
import com.app.finalproject.repositories.ICandidatRepository;
import com.app.finalproject.repositories.IProcessStateRepository;
import com.app.finalproject.services.ICloudinaryService;
import com.app.finalproject.services.IImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatService implements ICandidatService {


    private ICandidatRepository candidatRepository;
    private IBootcampRepository bootcampRepository;

    private IProcessStateRepository processStateRepository;

    ICloudinaryService cloudinaryService;

    IImageService imageService;
    public CandidatService(ICandidatRepository candidatRepository, IBootcampRepository bootcampRepository, IProcessStateRepository processStateRepository) {
        this.candidatRepository = candidatRepository;
        this.bootcampRepository = bootcampRepository;
        this.processStateRepository = processStateRepository;
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

        return new CandidatMapper().mapMultipleCandidatsToRes(candidatRepository.getCandidatsByBootcampId(id), auth);
    }

    @Override
    public List<CandidatRes> findByProcessCandidats(Long id, User auth) {

        return new CandidatMapper().mapMultipleCandidatsToRes(candidatRepository.getCandidatsByProcessId(id), auth);
    }

    @Override
    public CandidatRes findById(Long id, User auth) {
        Optional<Candidat> foundCandidat = candidatRepository.findById(id);
        if(foundCandidat.isEmpty()) throw new NotFoundException("Candidato no encontrado", "C-402");
        CandidatRes resCandidat = new CandidatMapper().mapToRes(foundCandidat.get(), auth);
        return resCandidat;
    }
    @Override
    public List<CandidatRes> findCandidatesByBootcampId(Long id, User authUser) {
        return new CandidatMapper().mapMultipleCandidatsToRes(candidatRepository.getCandidatsByBootcampId(id), authUser);
    }

    @Override
    public Candidat create(CandidatReq candidatReq, User auth) {

        var bootcamp = bootcampRepository.findByBootcampName(candidatReq.getBootcamp());
        var process = processStateRepository.findByName(candidatReq.getProcessState());

        Candidat createdCandidat = new CandidatMapper().mapRequestToCandidatToCreate(candidatReq, bootcamp.get(), process.get());

        return candidatRepository.save(createdCandidat);
    }

    @Override
    public CandidatRes updateACandidat(CandidatReq candidatReq, Long id, User auth){

        var candidat = candidatRepository.findById(id);
        var bootcamp = bootcampRepository.findByBootcampName(candidatReq.getBootcamp());
        var process = processStateRepository.findByName(candidatReq.getProcessState());

        if(candidat.isEmpty()) throw new NotFoundException("Candidato no encontrado", "C-402");

        Candidat updatedCandidat = new CandidatMapper().mapRequestToCandidatToEdit(candidatReq, candidat.get(), bootcamp.get(), process.get());
        candidatRepository.save(updatedCandidat);
        CandidatRes candidatRes = new CandidatMapper().mapToRes(updatedCandidat, auth);


        return candidatRes;


    }

    @Override
    public CandidatRes deleteCandidat(Long id, User auth) throws IOException {
        Candidat candidat = this.candidatRepository.findById(id).get();
        CandidatRes resCandidat = new CandidatMapper().mapToRes(candidat, auth);
        this.candidatRepository.delete(candidat);
        return resCandidat;
    }

}
