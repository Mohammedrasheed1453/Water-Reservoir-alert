package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "readings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @NotNull(message = "Water level is required")
    private Double waterLevel;

    private Instant timestamp = Instant.now();
}