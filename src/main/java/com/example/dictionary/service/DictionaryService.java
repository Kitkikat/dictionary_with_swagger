package com.example.dictionary.service;

import com.example.dictionary.model.Dictionary;

import java.util.List;


public interface DictionaryService {

    Dictionary saveDictionary(Dictionary dictionary);
    Dictionary findDictionaryById(Long id);
    Dictionary updateDictionary(Long id, Dictionary dictionary);
    Dictionary deleteDictionary(Long id);
    List<Dictionary> findAllDictionary();

}