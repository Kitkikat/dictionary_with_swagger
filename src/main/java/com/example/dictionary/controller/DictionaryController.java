package com.example.dictionary.controller;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionaries")
@Tag(name = "Dictionary", description = "Dictionary management APIs")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "Create a new dictionary")
    @ApiResponse(responseCode = "200", description = "Dictionary created successfully")
    @PostMapping
    public ResponseEntity<Dictionary> createDictionary(@RequestBody Dictionary dictionary) {
        Dictionary savedDictionary = dictionaryService.saveDictionary(dictionary);
        return ResponseEntity.ok(savedDictionary);
    }

    @Operation(summary = "Get a dictionary by ID")
    @ApiResponse(responseCode = "200", description = "Found the dictionary", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Dictionary.class))})
    @ApiResponse(responseCode = "404", description = "Dictionary not found")
    @GetMapping("/{id}")
    public ResponseEntity<Dictionary> getDictionaryById(@PathVariable Long id) {
        Dictionary dictionary = dictionaryService.findDictionaryById(id);
        if (dictionary != null) {
            return ResponseEntity.ok(dictionary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a dictionary")
    @ApiResponse(responseCode = "200", description = "Dictionary updated successfully")
    @ApiResponse(responseCode = "404", description = "Dictionary not found")
    @PutMapping("/{id}")
    public ResponseEntity<Dictionary> updateDictionary(@PathVariable Long id, @RequestBody Dictionary dictionary) {
        Dictionary updatedDictionary = dictionaryService.updateDictionary(id, dictionary);
        if (updatedDictionary != null) {
            return ResponseEntity.ok(updatedDictionary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a dictionary")
    @ApiResponse(responseCode = "204", description = "Dictionary deleted successfully")
    @ApiResponse(responseCode = "404", description = "Dictionary not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDictionary(@PathVariable Long id) {
        Dictionary deletedDictionary = dictionaryService.deleteDictionary(id);
        if (deletedDictionary != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all dictionaries")
    @ApiResponse(responseCode = "200", description = "List of dictionaries", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Dictionary.class))})
    @GetMapping
    public ResponseEntity<List<Dictionary>> getAllDictionaries() {
        List<Dictionary> dictionaries = dictionaryService.findAllDictionary();
        return ResponseEntity.ok(dictionaries);
    }
}
