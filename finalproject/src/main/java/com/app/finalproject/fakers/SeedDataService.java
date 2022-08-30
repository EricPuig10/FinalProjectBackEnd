package com.app.finalproject.fakers;

import com.app.finalproject.repositories.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Component
public class SeedDataService {

    private IBootcampRepository bootcampRepository;

    private IProcessStateRepository processStateRepository;

    private ICandidatRepository candidatRepository;
    private AuthRepository authRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public SeedDataService(IBootcampRepository bootcampRepository, IProcessStateRepository processStateRepository, ICandidatRepository candidatRepository, AuthRepository authRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.bootcampRepository = bootcampRepository;
        this.processStateRepository = processStateRepository;
        this.candidatRepository = candidatRepository;
        this.authRepository = authRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    @PostConstruct
    public void addData(){
        this.createBootcamp();
        this.createBootcamp2();
        this.createMultipleCandidates();
    }



    public Bootcamp createBootcamp (){
        var bootcamp = new Bootcamp();
        bootcamp.setBootcampname("OSONA");
        bootcampRepository.save(bootcamp);
        return bootcamp;
    }

    public Bootcamp createBootcamp2 (){
        var bootcamp = new Bootcamp();
        bootcamp.setBootcampname("FEMTECH");
        bootcampRepository.save(bootcamp);
        return bootcamp;
    }

    public Candidat createCandidat(Long age, String email, boolean assist, String bootcampname, String gender, String code, String name, String lastname, String secondlastname, String laboral, String nation, Long phone, String solo /*, String processState*/){
        var candidat = new Candidat();

        candidat.setAge(age);
        candidat.setEmail(email);
        candidat.setAssistedtoinformativesession(assist);
        candidat.setBootcamp(bootcampRepository.findByBootcampname(bootcampname).get());
        candidat.setGender(gender);
        candidat.setCodeacademyprogress(code);
        candidat.setName(name);
        candidat.setLastname(lastname);
        candidat.setSecondlastname(secondlastname);
        candidat.setLaboralsituation(laboral);
        candidat.setNationality(nation);
        candidat.setPhone(phone);
        candidat.setSololearnprogress(solo);
        //candidat.setProcessState(processStateRepository.findByName(processState).get());

        return candidat;
    }


    public void createMultipleCandidates(){
        List<Candidat> candidats = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Candidat>> typeReference = new TypeReference<List<Candidat>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/candidats.json");
        try{
            List<Candidat> candidatReq = mapper.readValue(inputStream, typeReference);
            candidatReq.forEach(req -> candidats.add(this.createCandidat(req.getAge(), req.getEmail(), req.isAssistedtoinformativesession(), req.getBootcamp().getBootcampname(), req.getGender(), req.getCodeacademyprogress(), req.getName(), req.getLastname(), req.getSecondlastname(),  req.getLaboralsituation(), req.getNationality(), req.getPhone(), req.getSololearnprogress() /*,req.getProcessState().toString() */)));
            candidatRepository.saveAll(candidats);
            System.out.println("Candidats saved!");
        }catch (IOException e){
            System.out.println("Unable to save candidats: "+ e.getMessage());
        }
    }




}
