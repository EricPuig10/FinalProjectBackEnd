package com.app.finalproject.repositories;

import com.app.finalproject.models.Bootcamp;
import com.app.finalproject.models.ProcessState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProcessStateRepository extends JpaRepository<ProcessState, Long> {

    Optional<ProcessState> findByName(String name);
}
