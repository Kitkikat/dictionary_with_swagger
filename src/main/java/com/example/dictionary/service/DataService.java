package com.example.dictionary.service;

import com.example.dictionary.model.Data;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.repository.DataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DataService {

    private final DataRepository dataRepository;
    private final DictionaryService dictionaryService;

    public DataService(DataRepository dataRepository, DictionaryService dictionaryService) {
        this.dataRepository = dataRepository;
        this.dictionaryService = dictionaryService;
    }

    public Data saveData(Data data) {
        // Если Dictionary уже существует, установите его
        Dictionary dictionary = data.getDictionary();
        if (dictionary != null && dictionary.getId() != null) {
            dictionary = dictionaryService.findDictionaryById(dictionary.getId());
            if (dictionary != null) {
                data.setDictionary(dictionary);
            }
        }
        return dataRepository.save(data);
    }

    public Optional<Data> findDataById(UUID id) {
        return dataRepository.findById(id);
    }

    public Optional<Data> updateData(UUID id, Data newData) {
        return dataRepository.findById(id).map(data -> {
            data.setDictionary(newData.getDictionary());
            data.setCode(newData.getCode());
            data.setValue(newData.getValue());
            return dataRepository.save(data);
        });
    }

    public boolean deleteData(UUID id) {
        return dataRepository.findById(id).map(data -> {
            dataRepository.delete(data);
            return true;
        }).orElse(false);
    }

    public List<Data> findAllData() {
        return dataRepository.findAll();
    }
}
