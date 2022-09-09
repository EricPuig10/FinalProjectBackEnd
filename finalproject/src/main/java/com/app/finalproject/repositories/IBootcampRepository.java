package com.app.finalproject.repositories;

import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBootcampRepository extends JpaRepository <Bootcamp, Long> {

    Optional<Bootcamp> findByBootcampName(String bootcampName);



}
