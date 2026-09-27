package com.example.demo.service;

import com.example.demo.model.Reading;
import com.example.demo.model.Station;
import com.example.demo.repository.ReadingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReadingServiceTest {

    @Mock
    private ReadingRepository readingRepository;

    @Mock
    private StationService stationService;

    @Mock
    private AlertService alertService;

    @InjectMocks
    private ReadingService readingService;

    private Station station;

    @BeforeEach
    void setUp() {
        station = new Station();
        station.setId(1L);
        station.setName("Krishna Reservoir");
        station.setLocation("Andhra Pradesh");
        station.setThreshold(85.0);
    }

    @Test
    void whenWaterLevelBelowThreshold_thenNoAlertIsRaised() {
        when(stationService.getStationById(1L)).thenReturn(station);
        when(readingRepository.save(any(Reading.class))).thenAnswer(inv -> inv.getArgument(0));

        readingService.recordReading(1L, 60.0);

        verify(alertService, never()).raiseAlert(any(), any());
    }

    @Test
    void whenWaterLevelAboveThreshold_thenAlertIsRaised() {
        when(stationService.getStationById(1L)).thenReturn(station);
        when(readingRepository.save(any(Reading.class))).thenAnswer(inv -> inv.getArgument(0));

        readingService.recordReading(1L, 95.0);

        verify(alertService, times(1)).raiseAlert(eq(station), any(Reading.class));
    }
}