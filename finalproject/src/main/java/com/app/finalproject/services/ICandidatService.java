package com.app.finalproject.services;
import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.dtos.candidats.CandidatResToDataTable;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;

import java.util.List;

public interface ICandidatService {
    List<CandidatResToDataTable> getAll(User auth);

    List<CandidatRes> findByBootcampCandidats(Long id, User authUser);

    List<CandidatRes> findByProcessCandidats(Long id, User authUser);

    CandidatRes findById(Long id, User auth);

    Candidat create(CandidatReq candidatReq, User authUser);


    CandidatRes updateACandidat(CandidatReq candidatReq, Long id, User authUser);

    CandidatRes deleteCandidat(Long id, User authUser);

    List<CandidatRes> findCandidatesByBootcampId(Long id, User authUser);
}
