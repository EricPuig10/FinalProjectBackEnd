package com.app.finalproject.services;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.User;

import java.util.List;

public interface ICandidatService {
    List<CandidatRes> getAll(User auth);

    List<CandidatRes> findByBootcampCandidats(Long id, User authUser);

    List<CandidatRes> findByProcessCandidats(Long id, User authUser);

    List<CandidatRes> findCandidatesByBootcampId(Long id, User authUser);
}
