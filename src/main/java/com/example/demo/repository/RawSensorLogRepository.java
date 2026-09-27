package com.example.demo.repository;

import com.example.demo.model.RawSensorLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawSensorLogRepository extends MongoRepository<RawSensorLog, String> {
}