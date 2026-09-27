
package com.example.demo.repository;

import com.example.demo.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    List<Reading> findByStationId(Long stationId);
}