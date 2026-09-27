package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "raw_sensor_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RawSensorLog {

    @Id
    private String id;

    private Long stationId;

    private Map<String, Object> payload;

    private Instant receivedAt = Instant.now();
}