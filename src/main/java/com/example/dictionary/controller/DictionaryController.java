package com.example.dictionary.controller;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.service.DictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Create a new dictionary",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dictionary object that needs to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Dictionary.class),
                            examples = @ExampleObject(
                                    name = "CreateDictionaryExample",
                                    value = "{ \"code\": \"sample_code\", \"description\": \"Sample Description\" }"
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Dictionary created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dictionary.class)))
    @PostMapping
    public ResponseEntity<Dictionary> createDictionary(@RequestBody Dictionary dictionary) {
        try {
            Dictionary savedDictionary = dictionaryService.saveDictionary(dictionary);
            return ResponseEntity.ok(savedDictionary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок сохранения
        }
    }

    @Operation(summary = "Get dictionary by ID")
    @ApiResponse(responseCode = "200", description = "Found the dictionary", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dictionary.class), examples = @ExampleObject(value = "{ \"id\": 1, \"code\": \"sample_code\", \"description\": \"Sample Description\" }")))
    @ApiResponse(responseCode = "404", description = "Dictionary not found")
    @GetMapping("/{id}")
    public ResponseEntity<Dictionary> getDictionaryById(@PathVariable Long id) {
        try {
            Dictionary dictionary = dictionaryService.findDictionaryById(id);
            return dictionary != null ? ResponseEntity.ok(dictionary) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при поиске
        }
    }

    @Operation(summary = "Update dictionary",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dictionary object that needs to be updated",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Dictionary.class),
                            examples = @ExampleObject(
                                    name = "UpdateDictionaryExample",
                                    value = "{ \"code\": \"updated_code\", \"description\": \"Updated Description\" }"
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Dictionary updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dictionary.class)))
    @ApiResponse(responseCode = "404", description = "Dictionary not found")
    @PutMapping("/{id}")
    public ResponseEntity<Dictionary> updateDictionary(@PathVariable Long id, @RequestBody Dictionary dictionary) {
        try {
            Dictionary updatedDictionary = dictionaryService.updateDictionary(id, dictionary);
            return updatedDictionary != null ? ResponseEntity.ok(updatedDictionary) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при обновлении
        }
    }

    @Operation(summary = "Delete dictionary")
    @ApiResponse(responseCode = "204", description = "Dictionary deleted successfully")
    @ApiResponse(responseCode = "404", description = "Dictionary not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDictionary(@PathVariable Long id) {
        try {
            boolean deleted = dictionaryService.deleteDictionary(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при удалении
        }
    }

    @Operation(summary = "Get all dictionaries")
    @ApiResponse(responseCode = "200", description = "Found all dictionaries", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dictionary.class), examples = @ExampleObject(value = "[{ \"id\": 1, \"code\": \"sample_code\", \"description\": \"Sample Description\" }, { \"id\": 2, \"code\": \"sample_code2\", \"description\": \"Sample Description2\" }]")))
    @GetMapping
    public ResponseEntity<List<Dictionary>> getAllDictionaries() {
        try {
            List<Dictionary> dictionaries = dictionaryService.findAllDictionary();
            return ResponseEntity.ok(dictionaries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при получении всех словарей
        }
    }
}
