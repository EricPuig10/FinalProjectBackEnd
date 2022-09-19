package com.app.finalproject.services;

import com.app.finalproject.dtos.process.ProcessRes;
import com.app.finalproject.models.Category;
import com.app.finalproject.models.ProcessState;
import com.app.finalproject.models.User;

import java.util.List;

public interface IProcessService {

    List<ProcessRes> getAll();
}
