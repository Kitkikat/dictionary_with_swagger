package com.example.dictionary.controller;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary")
@Tag(name = "Dictionary", description = "APIs for Dictionary management")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "Create a new dictionary")
    @PostMapping
    public ResponseEntity<Dictionary> createDictionary(@RequestBody Dictionary dictionary) {
        Dictionary savedDictionary = dictionaryService.saveDictionary(dictionary);
        return ResponseEntity.ok(savedDictionary);
    }

    @Operation(summary = "Get dictionary by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Dictionary> getDictionaryById(@PathVariable Long id) {
        Dictionary dictionary = dictionaryService.findDictionaryById(id);
        return dictionary != null ? ResponseEntity.ok(dictionary) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update dictionary")
    @PutMapping("/{id}")
    public ResponseEntity<Dictionary> updateDictionary(@PathVariable Long id, @RequestBody Dictionary dictionary) {
        Dictionary updatedDictionary = dictionaryService.updateDictionary(id, dictionary);
        return updatedDictionary != null ? ResponseEntity.ok(updatedDictionary) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete dictionary")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDictionary(@PathVariable Long id) {
        boolean deleted = dictionaryService.deleteDictionary(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all dictionaries")
    @GetMapping
    public ResponseEntity<List<Dictionary>> getAllDictionaries() {
        List<Dictionary> dictionaries = dictionaryService.findAllDictionary();
        return ResponseEntity.ok(dictionaries);
    }
}
