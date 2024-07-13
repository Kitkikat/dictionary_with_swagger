package com.example.dictionary.controller;

import com.example.dictionary.model.Data;
import com.example.dictionary.service.DataService;
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
@RequestMapping("/api/data")
@Tag(name = "Data", description = "Data management APIs")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

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
    public ResponseEntity<Data> getDataById(@PathVariable Long id) {
        Data data = dataService.findDataById(id);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update data")
    @ApiResponse(responseCode = "200", description = "Data updated successfully")
    @ApiResponse(responseCode = "404", description = "Data not found")
    @PutMapping("/{id}")
    public ResponseEntity<Data> updateData(@PathVariable Long id, @RequestBody Data data) {
        Data updatedData = dataService.updateData(id, data);
        if (updatedData != null) {
            return ResponseEntity.ok(updatedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete data")
    @ApiResponse(responseCode = "204", description = "Data deleted successfully")
    @ApiResponse(responseCode = "404", description = "Data not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        Data deletedData = dataService.deleteData(id);
        if (deletedData != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all data")
    @ApiResponse(responseCode = "200", description = "List of data", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Data.class))})
    @GetMapping
    public ResponseEntity<List<Data>> getAllData() {
        List<Data> dataList = dataService.findAllData();
        return ResponseEntity.ok(dataList);
    }
}
