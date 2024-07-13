package com.example.dictionary.service.Impl;

import com.example.dictionary.service.DictionaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.repository.DictionaryRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class DictionaryServiceImpl implements DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    @Override
    public Dictionary saveDictionary(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    @Override
    public Dictionary findDictionaryById(Long id) {
        return dictionaryRepository.findById(id).orElse(null);
    }

    @Override
    public Dictionary updateDictionary(Long id, Dictionary dictionary) {
        Dictionary updateDictionary = dictionaryRepository.findById(id).orElse(null);

        if (updateDictionary != null) {
            updateDictionary.setCode(dictionary.getCode());
            updateDictionary.setDescription(dictionary.getDescription());

            return dictionaryRepository.save(updateDictionary);
        }

        return null;
    }

    @Override
    public Dictionary deleteDictionary(Long id) {
        Dictionary deletedDictionary = dictionaryRepository.findById(id).orElse(null);

        if (deletedDictionary != null) {
            dictionaryRepository.delete(deletedDictionary);
        }

        return deletedDictionary;
    }

    @Override
    public List<Dictionary> findAllDictionary() {
        return dictionaryRepository.findAll();
    }

}
