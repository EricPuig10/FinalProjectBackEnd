package com.app.finalproject.fakers;

import com.app.finalproject.dtos.bootcamp.BootcampJasonRequest;
import com.app.finalproject.dtos.candidats.JsonRequest;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.repositories.*;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.Candidat;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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
        this.createProcessState();
        this.createProcessState2();
        this.createMultipleBootcamps();
        this.createMultipleCandidats();

    }

    public ProcessState createProcessState (){
        var process = new ProcessState();
        process.setName("First process");
        processStateRepository.save(process);
        return  process;
    }

    public ProcessState createProcessState2 (){
        var process = new ProcessState();
        process.setName("Second process");
        processStateRepository.save(process);
        return  process;
    }

    public Bootcamp createBootcamp(String bootcampName, String type, String duration, String characteristics, boolean isPresential) {
        var bootcamp = new Bootcamp();

        bootcamp.setBootcampName(bootcampName);
        bootcamp.setType(type);
        bootcamp.setDuration(duration);
        bootcamp.setCharacteristics(characteristics);
        bootcamp.setPresential(isPresential);

        return bootcamp;
    }

    public void createMultipleBootcamps(){
        List<Bootcamp> bootcamps = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<BootcampJasonRequest>> typeReference = new TypeReference<List<BootcampJasonRequest>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/bootcamps.json");
        try{
            List<BootcampJasonRequest> bootcampReq = mapper.readValue(inputStream, typeReference);
            bootcampReq.forEach(req -> bootcamps.add(this.createBootcamp(req.getBootcampName(), req.getType(), req.getDuration(), req.getCharacteristics(), req.isPresential())));
            bootcampRepository.saveAll(bootcamps);
        }catch (IOException | NoSuchElementException e) {}

    }


    public Candidat createCandidat(
            String name,
            String lastname,
            String secondlastname,
            String email,
            Long phone,
            Long age,
            String gender,
            String nation,
            String laboral,
            String solo ,
            String code,
            boolean assist,
            String bootcampName,
            String processState){

        System.out.println("Hola");

        var candidat = new Candidat();

        candidat.setName(name);
        candidat.setLastname(lastname);
        candidat.setSecondlastname(secondlastname);
        candidat.setEmail(email);
        candidat.setPhone(phone);
        candidat.setAge(age);
        candidat.setGender(gender);
        candidat.setNationality(nation);
        candidat.setLaboralsituation(laboral);
        candidat.setSololearnprogress(solo);
        candidat.setCodeacademyprogress(code);
        candidat.setAssistedtoinformativesession(assist);
        System.out.println(bootcampRepository.findByBootcampName(bootcampName).get());
        candidat.setBootcamp(bootcampRepository.findByBootcampName(bootcampName).get());
        candidat.setProcessState(processStateRepository.findByName(processState).get());

        System.out.println(candidat.getName());
        return candidat;
    }

    public void createMultipleCandidats(){
        List<Candidat> candidats = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonRequest>> typeReference = new TypeReference<List<JsonRequest>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/candidats.json");
        try{
            List<JsonRequest> candidatReq = mapper.readValue(inputStream, typeReference);
            candidatReq.forEach(req -> candidats.add(this.createCandidat(
                    req.getName(),
                    req.getLastname(),
                    req.getSecondlastname(),
                    req.getEmail(),
                    req.getPhone(),
                    req.getAge(),
                    req.getGender(),
                    req.getNationality(),
                    req.getLaboralsituation(),
                    req.getSololearnprogress(),
                    req.getCodeacademyprogress(),
                    req.isAssistedtoinformativesession(),
                    req.getBootcampName(),
                    req.getProcessState()
            )));
            candidatRepository.saveAll(candidats);
            System.out.println("Candidats saved!");
        }catch (IOException | NoSuchElementException e){
            System.out.println("Unable to save candidats: "+ e.getMessage());
        }
    }





}
