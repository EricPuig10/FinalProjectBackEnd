package com.app.finalproject.mappers;

import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;

public class CandidatMapper {
    public CandidatRes mapToRes(Candidat candidat, User auth){
        CandidatRes resCandidat = new CandidatRes();
        resCandidat.setAge(candidat.getAge());
        resCandidat.setEmail(candidat.getEmail());
        resCandidat.setGender(candidat.getGender());
        resCandidat.setLaboralsituation(candidat.getLaboralsituation());
        resCandidat.setCodeacademyprogress(candidat.getCodeacademyprogress());
        resCandidat.setName(candidat.getName());
        resCandidat.setLastname(candidat.getLastname());
        resCandidat.setSecondlastname(candidat.getSecondlastname());
        resCandidat.setNationality(candidat.getNationality());
        resCandidat.setPhone(candidat.getPhone());
        resCandidat.setId(candidat.getId());
        resCandidat.setSololearnprogress(candidat.getSololearnprogress());
        resCandidat.setAssistedtoinformativesession(candidat.isAssistedtoinformativesession());
        resCandidat.setBootcamp(candidat.getBootcamp());
        resCandidat.setProcessState(candidat.getProcessState());
        return  resCandidat;
    }
}
