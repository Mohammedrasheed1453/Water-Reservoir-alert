package com.example.demo.controller;

import com.example.demo.model.Reading;
import com.example.demo.service.ReadingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stations/{stationId}/readings")
public class ReadingController {

    private final ReadingService readingService;

    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @PostMapping
    public ResponseEntity<Reading> recordReading(
            @PathVariable Long stationId,
            @RequestBody Map<String, Double> body) {

        Double waterLevel = body.get("waterLevel");
        Reading saved = readingService.recordReading(stationId, waterLevel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Reading>> getReadingsByStation(@PathVariable Long stationId) {
        return ResponseEntity.ok(readingService.getReadingsByStation(stationId));
    }
}