package com.app.finalproject.mappers;

import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;

import java.util.ArrayList;
import java.util.List;

public class CandidatMapper {

    public CandidatRes mapToRes(Candidat candidat, User auth) {
        CandidatRes resCandidat = new CandidatRes();
        resCandidat.setAge(candidat.getAge());
        resCandidat.setEmail(candidat.getEmail());
        resCandidat.setGender(candidat.getGender());
        resCandidat.setLaboralsituation(candidat.getLaboralsituation());
        //resCandidat.setCodeacademyprogress(candidat.getCodeacademyprogress());
        resCandidat.setName(candidat.getName());
        resCandidat.setLastname(candidat.getLastname());
        resCandidat.setSecondlastname(candidat.getSecondlastname());
        resCandidat.setNationality(candidat.getNationality());
        resCandidat.setPhone(candidat.getPhone());
        resCandidat.setId(candidat.getId());
        //resCandidat.setSololearnprogress(candidat.getSololearnprogress());
        //resCandidat.setAssistedtoinformativesession(candidat.isAssistedtoinformativesession());
        resCandidat.setBootcamp(candidat.getBootcamp());
        resCandidat.setProcessState(candidat.getProcessState());
        return resCandidat;
    }


    public List<CandidatRes> mapMultipleCandidatsToRes(List<Candidat> candidats, User auth) {
        List<CandidatRes> res = new ArrayList<>();
        candidats.forEach(Candidat -> res.add(this.mapToRes(Candidat, auth)));
        return res;
    }



    public Candidat mapRequestToCandidatToEdit(CandidatReq candidatReq, Candidat candidat, Bootcamp bootcamp, ProcessState process) {
        candidat.setName(candidatReq.getName());
        candidat.setLastname(candidatReq.getLastname());
        candidat.setSecondlastname(candidatReq.getSecondlastname());
        candidat.setEmail(candidatReq.getEmail());
        candidat.setBootcamp(bootcamp);
        candidat.setProcessState(process);
        candidat.setPhone(candidatReq.getPhone());
        candidat.setAge(candidatReq.getAge());
        candidat.setNationality(candidatReq.getNationality());
        candidat.setLaboralsituation(candidatReq.getLaboralsituation());
        candidat.setGender(candidatReq.getGender());
        return candidat;

    }

    public Candidat mapRequestToCandidatToCreate(CandidatReq candidatReq, Bootcamp bootcamp, ProcessState process) {
        Candidat candidat = new Candidat();
        candidat.setName(candidatReq.getName());
        candidat.setLastname(candidatReq.getLastname());
        candidat.setSecondlastname(candidatReq.getSecondlastname());
        candidat.setEmail(candidatReq.getEmail());
        candidat.setBootcamp(bootcamp);
        candidat.setProcessState(process);
        candidat.setPhone(candidatReq.getPhone());
        candidat.setAge(candidatReq.getAge());
        candidat.setNationality(candidatReq.getNationality());
        candidat.setLaboralsituation(candidatReq.getLaboralsituation());
        candidat.setGender(candidatReq.getGender());
        return candidat;

    }



}
