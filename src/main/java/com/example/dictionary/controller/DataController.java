package com.example.dictionary.controller;

import com.example.dictionary.model.Data;
import com.example.dictionary.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/data")
@Tag(name = "Data", description = "Data management APIs")
@AllArgsConstructor
public class DataController {

    private final DataService dataService;

    @Operation(summary = "Create new data")
    @ApiResponse(responseCode = "200", description = "Data created successfully")
    @PostMapping
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        Data savedData = dataService.saveData(data);
        return ResponseEntity.ok(savedData);
    }

    @Operation(summary = "Get data by ID")
    @ApiResponse(responseCode = "200", description = "Found the data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Data.class))})
    @ApiResponse(responseCode = "404", description = "Data not found")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Data>> getDataById(@PathVariable Long id) {
        Optional<Data> data = dataService.findDataById(id);
        return data.isPresent() ? ResponseEntity.ok(data) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update data")
    @ApiResponse(responseCode = "200", description = "Data updated successfully")
    @ApiResponse(responseCode = "404", description = "Data not found")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Data>> updateData(@PathVariable Long id, @RequestBody Data data) {
        Optional<Data> updatedData = dataService.updateData(id, data);
        return updatedData.isPresent() ? ResponseEntity.ok(updatedData) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete data")
    @ApiResponse(responseCode = "204", description = "Data deleted successfully")
    @ApiResponse(responseCode = "404", description = "Data not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        boolean deleted = dataService.deleteData(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all data")
    @ApiResponse(responseCode = "200", description = "Found all data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Data.class))})
    @GetMapping
    public ResponseEntity<List<Data>> getAllData() {
        List<Data> dataList = dataService.findAllData();
        return ResponseEntity.ok(dataList);
    }
}
