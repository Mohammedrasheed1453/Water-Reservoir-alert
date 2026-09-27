package com.example.demo.controller;

import com.example.demo.model.Alert;
import com.example.demo.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts(
            @RequestParam(required = false) String severity) {
        if (severity != null) {
            return ResponseEntity.ok(alertService.getAlertsBySeverity(severity));
        }
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
    @GetMapping("/summary")
public ResponseEntity<Map<String, Long>> getAlertSummary() {
    return ResponseEntity.ok(alertService.getAlertSummary());
}
}