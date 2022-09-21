package com.app.finalproject.mappers;

import com.app.finalproject.dtos.candidats.CandidatReq;
import com.app.finalproject.dtos.candidats.CandidatRes;
import com.app.finalproject.models.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CandidatMapper {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public CandidatRes mapToRes(Candidat candidat, User auth) {
        CandidatRes resCandidat = new CandidatRes();


        resCandidat.setAge(candidat.getAge());
        resCandidat.setEmail(candidat.getEmail());
        resCandidat.setGender(String.valueOf(candidat.getGender()));
        resCandidat.setDegree(candidat.getDegree());
        resCandidat.setDate((candidat.getDate()));
        resCandidat.setEnglish(candidat.getEnglish());
        resCandidat.setFormation(candidat.getFormation());
        resCandidat.setMotivation(candidat.getMotivation());
        resCandidat.setReached(candidat.getReached());
        resCandidat.setDirection(candidat.getDirection());
        resCandidat.setSpirit(candidat.getSpirit());
        resCandidat.setSuperpower(candidat.getSuperpower());
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
        resCandidat.setImg(candidat.getImg());
        resCandidat.setLocation(candidat.getLocation());
        resCandidat.setDocument(String.valueOf(candidat.getDocument()));
        resCandidat.setNumberdocument(candidat.getNumberdocument());
        resCandidat.setSololearnprogress(candidat.getSololearnprogress());
        resCandidat.setCodeacademyprogress(candidat.getCodeacademyprogress());
        resCandidat.setAssistedtoinformativesession(candidat.isAssistedtoinformativesession());

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
        candidat.setDegree(candidatReq.getDegree());
        candidat.setDate((candidatReq.getDate()));
        candidat.setEnglish(candidatReq.getEnglish());
        candidat.setFormation(candidatReq.getFormation());
        candidat.setMotivation(candidatReq.getMotivation());
        candidat.setReached(candidatReq.getReached());
        candidat.setDirection(candidatReq.getDirection());
        candidat.setSpirit(candidatReq.getSpirit());
        candidat.setSuperpower(candidatReq.getSuperpower());
        candidat.setProcessState(process);
        candidat.setPhone(candidatReq.getPhone());
        candidat.setAge(candidatReq.getAge());
        candidat.setNationality(candidatReq.getNationality());
        candidat.setLaboralsituation(candidatReq.getLaboralsituation());
        candidat.setGender(Gender.valueOf(candidatReq.getGender()));
        candidat.setImg(candidatReq.getImg());
        candidat.setLocation(candidatReq.getLocation());
        candidat.setDocument(Document.valueOf(candidatReq.getDocument()));
        candidat.setNumberdocument(candidatReq.getNumberdocument());
        candidat.setSololearnprogress(candidatReq.getSololearnprogress());
        candidat.setCodeacademyprogress(candidatReq.getCodeacademyprogress());
        candidat.setAssistedtoinformativesession(candidatReq.isAssistedtoinformativesession());

        return candidat;

    }

    public Candidat mapRequestToCandidatToCreate(CandidatReq candidatReq, Bootcamp bootcamp, ProcessState process) {
        Candidat candidat = new Candidat();
        candidat.setName(candidatReq.getName());
        candidat.setLastname(candidatReq.getLastname());
        candidat.setSecondlastname(candidatReq.getSecondlastname());
        candidat.setEmail(candidatReq.getEmail());
        candidat.setBootcamp(bootcamp);
        candidat.setDegree(candidatReq.getDegree());
        candidat.setDate((candidatReq.getDate()));
        candidat.setEnglish(candidatReq.getEnglish());
        candidat.setFormation(candidatReq.getFormation());
        candidat.setMotivation(candidatReq.getMotivation());
        candidat.setReached(candidatReq.getReached());
        candidat.setDirection(candidatReq.getDirection());
        candidat.setSpirit(candidatReq.getSpirit());
        candidat.setSuperpower(candidatReq.getSuperpower());
        candidat.setProcessState(process);
        candidat.setPhone(candidatReq.getPhone());
        candidat.setAge(candidatReq.getAge());
        candidat.setNationality(candidatReq.getNationality());
        candidat.setLaboralsituation(candidatReq.getLaboralsituation());
        candidat.setGender(Gender.valueOf(candidatReq.getGender()));
        candidat.setImg(candidatReq.getImg());
        candidat.setLocation(candidatReq.getLocation());
        candidat.setDocument(Document.valueOf(candidatReq.getDocument()));
        candidat.setNumberdocument(candidatReq.getNumberdocument());
        candidat.setSololearnprogress(candidatReq.getSololearnprogress());
        candidat.setCodeacademyprogress(candidatReq.getCodeacademyprogress());
        candidat.setAssistedtoinformativesession(candidatReq.isAssistedtoinformativesession());
        return candidat;

    }



}
