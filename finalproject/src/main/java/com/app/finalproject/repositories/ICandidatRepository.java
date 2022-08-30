package com.app.finalproject.repositories;

import com.app.finalproject.models.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidatRepository extends JpaRepository<Candidat, Long> {
}
