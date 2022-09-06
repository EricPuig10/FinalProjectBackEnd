package com.app.finalproject.mappers;

import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatReqToEdit;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.dtos.candidats.CandidatResToDataTable;
import com.app.finalproject.models.Candidat;
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
        return resCandidat;
    }

    public CandidatResToDataTable mapToResData(Candidat candidat, User auth) {
        CandidatResToDataTable resCandidat = new CandidatResToDataTable();
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
        resCandidat.setBootcamp(candidat.getBootcamp().getBootcampName());
        resCandidat.setProcessState(candidat.getProcessState().getName());
        return resCandidat;
    }

    public List<CandidatRes> mapMultipleCandidatesToRes(List<Candidat> candidats, User auth) {
        List<CandidatRes> res = new ArrayList<>();
        candidats.forEach(Candidat -> res.add(this.mapToRes(Candidat, auth)));
        return res;
    }

    public Candidat mapRequestToCandidatToEdit(CandidatReq candidatReq, Candidat candidat) {
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
        return candidat;

    }



}
