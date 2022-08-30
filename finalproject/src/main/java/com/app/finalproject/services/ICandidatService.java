package com.app.finalproject.services;

import com.app.finalproject.models.Candidat;
import com.app.finalproject.models.User;

import java.util.List;

public interface ICandidatService {
    List<Candidat> getAll(User auth);
}
