package com.example.dictionary.service;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.repository.DictionaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public Dictionary findDictionaryById(Long id) {
        return dictionaryRepository.findById(id).orElse(null);
    }

    public List<Dictionary> findAllDictionary() {
        return dictionaryRepository.findAll();
    }

    public Dictionary saveDictionary(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    public Dictionary updateDictionary(Long id, Dictionary dictionary) {
        Dictionary existingDictionary = dictionaryRepository.findById(id).orElse(null);
        if (existingDictionary != null) {
            existingDictionary.setCode(dictionary.getCode());
            existingDictionary.setDescription(dictionary.getDescription());
            return dictionaryRepository.save(existingDictionary);
        }
        return null;
    }

    public boolean deleteDictionary(Long id) {
        Dictionary dictionary = dictionaryRepository.findById(id).orElse(null);
        if (dictionary != null) {
            dictionaryRepository.delete(dictionary);
            return true;
        }
        return false;
    }
}
