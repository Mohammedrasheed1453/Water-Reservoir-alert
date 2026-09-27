package com.example.demo.service;

import com.example.demo.model.RawSensorLog;
import com.example.demo.repository.RawSensorLogRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RawSensorLogService {

    private final RawSensorLogRepository rawSensorLogRepository;

    public RawSensorLogService(RawSensorLogRepository rawSensorLogRepository) {
        this.rawSensorLogRepository = rawSensorLogRepository;
    }

    public RawSensorLog storeRawLog(Long stationId, Map<String, Object> payload) {
        RawSensorLog log = new RawSensorLog();
        log.setStationId(stationId);
        log.setPayload(payload);
        return rawSensorLogRepository.save(log);
    }
}