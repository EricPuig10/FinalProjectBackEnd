package com.app.finalproject.services.candidatS;
import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;

import java.io.IOException;
import java.util.List;

public interface ICandidatService {
    List<CandidatRes> getAll(User auth);

//    List<CandidatRes> findByBootcampCandidats(Long id, User authUser);

    List<CandidatRes> findCandidatesByProcessId(Long id, User authUser);

    CandidatRes findById(Long id, User auth);

    Candidat create(CandidatReq candidatReq, User authUser);

    CandidatRes updateACandidat(CandidatReq candidatReq, Long id, User authUser);

    CandidatRes deleteCandidat(Long id, User authUser) throws IOException;

    List<CandidatRes> findCandidatesByBootcampId(Long id, User authUser);



}
