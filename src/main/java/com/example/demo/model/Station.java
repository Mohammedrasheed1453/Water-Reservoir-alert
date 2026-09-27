package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity 
@Table(name="stations")
@Getter 
@Setter 

@NoArgsConstructor 
@AllArgsConstructor 


public class Station {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Station name is required")
    private String name;

    @NotBlank (message = "Location is required")
    private String location;

    @NotNull(message = "Threshold is required")
    private Double threshold;

}
