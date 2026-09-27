package com.example.demo.service;

import com.example.demo.model.Reading;
import com.example.demo.model.Station;
import com.example.demo.repository.ReadingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {

    private final ReadingRepository readingRepository;
    private final StationService stationService;
    private final AlertService alertService;

    public ReadingService(ReadingRepository readingRepository,
                           StationService stationService,
                           AlertService alertService) {
        this.readingRepository = readingRepository;
        this.stationService = stationService;
        this.alertService = alertService;
    }

    public Reading recordReading(Long stationId, Double waterLevel) {
        Station station = stationService.getStationById(stationId);

        Reading reading = new Reading();
        reading.setStation(station);
        reading.setWaterLevel(waterLevel);

        Reading savedReading = readingRepository.save(reading);

        if (waterLevel > station.getThreshold()) {
            alertService.raiseAlert(station, savedReading);
        }

        return savedReading;
    }

    public List<Reading> getReadingsByStation(Long stationId) {
        return readingRepository.findByStationId(stationId);
    }
}