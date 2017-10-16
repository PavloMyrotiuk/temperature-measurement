package com.myrotiuk.smartfrogassignment.service;

import com.myrotiuk.smartfrogassignment.dao.MeasurementDao;
import com.myrotiuk.smartfrogassignment.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {
    @Autowired
    private MeasurementDao measurementDao;

    @Autowired
    private EventsService eventsService;

    public void processMeasurement(Measurement measurement) {
        Measurement saved = measurementDao.save(measurement);
        eventsService.processMeasurement(saved);
    }

    public double avarageTemperatureLastHour(String sensorUuid) {
        return measurementDao.lastHourMeasurements(sensorUuid).stream().mapToDouble(Measurement::getTemperature).average().orElse(0);
    }

    public double avarageTemperatureLast7Days(String sensorUuid) {
        return measurementDao.last7DaysMeasurements(sensorUuid).stream().mapToDouble(Measurement::getTemperature).average().orElse(0);
    }

    public double maxTemperatureLast30Days(String sensorUuid) {
        return measurementDao.last30DaysMeasurements(sensorUuid).stream().mapToDouble(Measurement::getTemperature).max().orElse(0);
    }
}
