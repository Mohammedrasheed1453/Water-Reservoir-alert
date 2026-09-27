package com.example.demo.service;

import com.example.demo.model.Alert;
import com.example.demo.model.Reading;
import com.example.demo.model.Station;
import com.example.demo.repository.AlertRepository;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;


@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert raiseAlert(Station station, Reading reading) {
        Alert alert = new Alert();
        alert.setStation(station);
        alert.setReading(reading);
        alert.setMessage("Water level " + reading.getWaterLevel() +
                " exceeds threshold " + station.getThreshold() +
                " at station " + station.getName());
        alert.setSeverity("HIGH");

        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public List<Alert> getAlertsBySeverity(String severity) {
        return alertRepository.findBySeverity(severity);
    }
    public Map<String, Long> getAlertSummary() {
    List<Alert> alerts = alertRepository.findAll();
    return alerts.stream()
            .collect(Collectors.groupingBy(Alert::getSeverity, Collectors.counting()));
}

}