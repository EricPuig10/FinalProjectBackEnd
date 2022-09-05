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
        this.createMultipleCandidats();
        this.createMultipleBootcamps();
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

    public Candidat createCandidat(Long age, String email, boolean assist, String bootcampName, String gender, String code, String name, String lastname, String secondlastname, String laboral, String nation, Long phone, String solo , String processState){
        var candidat = new Candidat();

        candidat.setAge(age);
        candidat.setEmail(email);
        candidat.setAssistedtoinformativesession(assist);
        candidat.setBootcamp(bootcampRepository.findByBootcampName(bootcampName).get());
        candidat.setGender(gender);
        candidat.setCodeacademyprogress(code);
        candidat.setName(name);
        candidat.setLastname(lastname);
        candidat.setSecondlastname(secondlastname);
        candidat.setLaboralsituation(laboral);
        candidat.setNationality(nation);
        candidat.setPhone(phone);
        candidat.setSololearnprogress(solo);
        candidat.setProcessState(processStateRepository.findByName(processState).get());

        return candidat;
    }

    public void createMultipleCandidats(){
        List<Candidat> candidats = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<JsonRequest>> typeReference = new TypeReference<List<JsonRequest>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/candidats.json");
        try{
            List<JsonRequest> candidatReq = mapper.readValue(inputStream, typeReference);
            candidatReq.forEach(req -> candidats.add(this.createCandidat(req.getAge(), req.getEmail(), req.isAssistedtoinformativesession(), req.getBootcampName(), req.getGender(), req.getCodeacademyprogress(), req.getName(), req.getLastname(), req.getSecondlastname(),  req.getLaboralsituation(), req.getNationality(), req.getPhone(), req.getSololearnprogress() , req.getProcessState() )));
            candidatRepository.saveAll(candidats);
            System.out.println("Candidats saved!");
        }catch (IOException | NoSuchElementException e){
            System.out.println("Unable to save candidats: "+ e.getMessage());
        }
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



}
