package com.example.dictionary.controller;

import com.example.dictionary.model.Data;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.service.DataService;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/data")
@Tag(name = "Data", description = "Data management APIs")
public class DataController {

    private final DataService dataService;
    private final DictionaryService dictionaryService;

    public DataController(DataService dataService, DictionaryService dictionaryService) {
        this.dataService = dataService;
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "Create new data",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data object that needs to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Data.class),
                            examples = @ExampleObject(
                                    name = "CreateDataExample",
                                    value = "{ \"dictionary\": { \"id\": 1 }, \"code\": \"test\", \"value\": \"example value\" }"
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Data created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Data.class)))
    @PostMapping
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        try {
            // Check if the dictionary exists, if not create a new one
            Dictionary dictionary = dictionaryService.findDictionaryById(data.getDictionary().getId());
            if (dictionary == null) {
                dictionary = dictionaryService.saveDictionary(data.getDictionary());
            }
            data.setDictionary(dictionary);

            Data savedData = dataService.saveData(data);
            return ResponseEntity.ok(savedData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок сохранения
        }
    }

    @Operation(summary = "Get data by ID")
    @ApiResponse(responseCode = "200", description = "Found the data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Data.class), examples = @ExampleObject(value = "{ \"id\": 1, \"dictionary\": { \"id\": 1 }, \"code\": \"test\", \"value\": \"example value\" }")))
    @ApiResponse(responseCode = "404", description = "Data not found")
    @GetMapping("/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable UUID id) {
        try {
            Optional<Data> data = dataService.findDataById(id);
            return data.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при поиске
        }
    }

    @Operation(summary = "Update data",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Data object that needs to be updated",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Data.class),
                            examples = @ExampleObject(
                                    name = "UpdateDataExample",
                                    value = "{ \"dictionary\": { \"id\": 1 }, \"code\": \"updated test\", \"value\": \"updated example value\" }"
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Data updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Data.class)))
    @ApiResponse(responseCode = "404", description = "Data not found")
    @PutMapping("/{id}")
    public ResponseEntity<Data> updateData(@PathVariable UUID id, @RequestBody Data data) {
        try {
            Optional<Data> updatedData = dataService.updateData(id, data);
            return updatedData.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при обновлении
        }
    }

    @Operation(summary = "Delete data")
    @ApiResponse(responseCode = "204", description = "Data deleted successfully")
    @ApiResponse(responseCode = "404", description = "Data not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable UUID id) {
        try {
            boolean deleted = dataService.deleteData(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при удалении
        }
    }

    @Operation(summary = "Get all data")
    @ApiResponse(responseCode = "200", description = "Found all data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Data.class), examples = @ExampleObject(value = "[{ \"id\": 1, \"dictionary\": { \"id\": 1 }, \"code\": \"test\", \"value\": \"example value\" }, { \"id\": 2, \"dictionary\": { \"id\": 1 }, \"code\": \"test2\", \"value\": \"example value2\" }]")))
    @GetMapping
    public ResponseEntity<List<Data>> getAllData() {
        try {
            List<Data> dataList = dataService.findAllData();
            return ResponseEntity.ok(dataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка ошибок при получении всех данных
        }
    }
}
