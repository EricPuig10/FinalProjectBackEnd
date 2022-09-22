package com.app.finalproject.fakers;

import com.app.finalproject.dtos.bootcamp.BootcampJasonRequest;
import com.app.finalproject.dtos.candidats.JsonRequest;
import com.app.finalproject.models.*;
import com.app.finalproject.repositories.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Component
public class SeedDataService {

    private IBootcampRepository bootcampRepository;

    private IProcessStateRepository processStateRepository;

    private ICategoryRepository categoryRepository;

    private ICandidatRepository candidatRepository;
    private AuthRepository authRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public SeedDataService(IBootcampRepository bootcampRepository, IProcessStateRepository processStateRepository, ICategoryRepository categoryRepository, ICandidatRepository candidatRepository, AuthRepository authRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.bootcampRepository = bootcampRepository;
        this.processStateRepository = processStateRepository;
        this.categoryRepository = categoryRepository;
        this.candidatRepository = candidatRepository;
        this.authRepository = authRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void addData(){
        this.createProcessState();
        this.createProcessState2();
        this.createCategory("Full Stack");
        this.createCategory("Front End");
        this.createCategory("AI");
        this.createCategory("Blockchain");
        this.createMultipleBootcamps();
        this.createMultipleCandidats();

    }

    public ProcessState createProcessState (){
        var process = new ProcessState();
        process.setName("First process");
        processStateRepository.save(process);
        return process;
    }



    public ProcessState createProcessState2 (){
        var process = new ProcessState();
        process.setName("Second process");
        processStateRepository.save(process);
        return process;
    }

//    public ProcessState createProcessState3() {
//        var process = new ProcessState();
//        process.setName("Seleccionado");
//        processStateRepository.save(process);
//        return process;
//    }

    public Category createCategory (String name){
        var category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return  category;
    }

    public Bootcamp createBootcamp(String bootcampName, Category type, String duration, String characteristics, String former, String coformer, Date initialDate, Date finalDate) {
        var bootcamp = new Bootcamp();

        bootcamp.setBootcampName(bootcampName);
        bootcamp.setDuration(duration);
        bootcamp.setCharacteristics(characteristics);
        bootcamp.setCategory(categoryRepository.findByName(type.getName()).get());
        bootcamp.setFormer(former);
        bootcamp.setCoformer(coformer);
        bootcamp.setInitialDate(initialDate);
        bootcamp.setFinalDate(finalDate);



        return bootcamp;
    }

    public void createMultipleBootcamps(){
        List<Bootcamp> bootcamps = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<BootcampJasonRequest>> typeReference = new TypeReference<List<BootcampJasonRequest>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/bootcamps.json");
        try{
            List<BootcampJasonRequest> bootcampReq = mapper.readValue(inputStream, typeReference);
            bootcampReq.forEach(req -> bootcamps.add(this.createBootcamp(req.getBootcampName(), req.getCategory(), req.getDuration(), req.getCharacteristics(), req.getFormer(), req.getCoformer(), req.getInitialDate(), req.getFinalDate())));
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
            String degree,
            Date date,
            String superpower,
            String direction,
            String english,
            String formation,
            String reached,
            String spirit,
            String motivation,
            String gender,
            String nation,
            String laboral,
            String solo ,
            String code,
            boolean assist,
            String bootcampName,
            String processState, String img,
            String location, String document, String numberdocument){


        var candidat = new Candidat();

        candidat.setName(name);
        candidat.setLastname(lastname);
        candidat.setSecondlastname(secondlastname);
        candidat.setEmail(email);
        candidat.setPhone(phone);
        candidat.setAge(age);
        candidat.setDegree(degree);
        candidat.setDate(date);
        candidat.setSuperpower(superpower);
        candidat.setDirection(direction);
        candidat.setEnglish(english);
        candidat.setFormation(formation);
        candidat.setReached(reached);
        candidat.setSpirit(spirit);
        candidat.setMotivation(motivation);
        candidat.setGender(Gender.valueOf((gender)));
        candidat.setNationality(nation);
        candidat.setLaboralsituation(laboral);
        candidat.setSololearnprogress(solo);
        candidat.setCodeacademyprogress(code);
        candidat.setAssistedtoinformativesession(assist);
        System.out.println(bootcampRepository.findByBootcampName(bootcampName).get());
        candidat.setBootcamp(bootcampRepository.findByBootcampName(bootcampName).get());
        candidat.setProcessState(processStateRepository.findByName(processState).get());
        candidat.setImg(img);
        candidat.setLocation(location);
        candidat.setDocument(Document.valueOf(document));
        candidat.setNumberdocument(numberdocument);

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
                    req.getDegree(),
                    req.getDate(),
                    req.getSuperpower(),
                    req.getDirection(),
                    req.getEnglish(),
                    req.getFormation(),
                    req.getReached(),
                    req.getSpirit(),
                    req.getMotivation(),
                    req.getGender(),
                    req.getNationality(),
                    req.getLaboralsituation(),
                    req.getSololearnprogress(),
                    req.getCodeacademyprogress(),
                    req.isAssistedtoinformativesession(),
                    req.getBootcampName(),
                    req.getProcessState(),
                    req.getImg(),
                    req.getLocation(),
                    req.getDocument(),
                    req.getNumberdocument()
            )));
            candidatRepository.saveAll(candidats);
            System.out.println("Candidats saved!");
        }catch (IOException | NoSuchElementException e){
            System.out.println("Unable to save candidats: "+ e.getMessage());
        }
    }





}
