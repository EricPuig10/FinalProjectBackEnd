package com.app.finalproject.repositories;

import com.app.finalproject.models.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICandidatRepository extends JpaRepository<Candidat, Long> {

    @Query("select c from Candidat c where c.bootcamp.id = :id")
    List<Candidat> getCandidatesByBootcampId(@Param("id") Long id);

    @Query("select c from Candidat c where c.processState.id = :id")
    List<Candidat> getCandidatesByProcessId(@Param("id") Long id);


    



}
