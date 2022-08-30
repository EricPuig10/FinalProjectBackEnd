package com.app.finalproject.repositories;

import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBootcampRepository extends JpaRepository <Bootcamp, Long> {

    Optional<Bootcamp> findByBootcampname(String bootcampname);

}
