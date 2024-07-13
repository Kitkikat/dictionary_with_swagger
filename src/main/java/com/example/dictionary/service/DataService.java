package com.example.dictionary.service;

import com.example.dictionary.model.Data;
import com.example.dictionary.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DataService {

    Data saveData(Data data);
    Data findDataById(Long id);
    List<Data> findAllData();
    Data updateData(Long id, Data data);
    Data deleteData(Long id);

}
