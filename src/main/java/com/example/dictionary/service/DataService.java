package com.example.dictionary.service;

import com.example.dictionary.model.Data;
import com.example.dictionary.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DataService {
    private final DataRepository dataRepository;

    public Optional<Data> findDataById(Long id) {
        return dataRepository.findById(id);
    }

    public List<Data> findAllData() {
        return dataRepository.findAll();
    }

    public Data saveData(Data data) {
        return dataRepository.save(data);
    }

    public Optional<Data> updateData(Long id, Data data) {
        return dataRepository.findById(id).map(existingData -> {
            existingData.setCode(data.getCode());
            existingData.setValue(data.getValue());
            existingData.setDictionary(data.getDictionary());
            return dataRepository.save(existingData);
        });
    }

    public boolean deleteData(Long id) {
        return dataRepository.findById(id).map(data -> {
            dataRepository.delete(data);
            return true;
        }).orElse(false);
    }
}
