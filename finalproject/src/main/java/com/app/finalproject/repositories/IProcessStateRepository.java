package com.app.finalproject.repositories;

import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.ProcessState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProcessStateRepository extends JpaRepository<ProcessState, Long> {

    Optional<ProcessState> findByName(String name);
}
