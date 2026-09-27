package com.example.demo.controller;

import com.example.demo.model.RawSensorLog;
import com.example.demo.service.RawSensorLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stations/{stationId}/raw-logs")
public class RawSensorLogController {

    private final RawSensorLogService rawSensorLogService;

    public RawSensorLogController(RawSensorLogService rawSensorLogService) {
        this.rawSensorLogService = rawSensorLogService;
    }

    @PostMapping
    public ResponseEntity<RawSensorLog> storeRawLog(
            @PathVariable Long stationId,
            @RequestBody Map<String, Object> payload) {

        RawSensorLog saved = rawSensorLogService.storeRawLog(stationId, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}