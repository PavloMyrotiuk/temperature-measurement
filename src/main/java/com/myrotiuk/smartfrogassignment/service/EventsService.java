package com.myrotiuk.smartfrogassignment.service;

import com.myrotiuk.smartfrogassignment.dao.EventsRepository;
import com.myrotiuk.smartfrogassignment.model.Event;
import com.myrotiuk.smartfrogassignment.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventsService {

    @Value("${temperature}")
    private double criticalTemerature;

    @Autowired
    private EventsRepository eventsRepository;

    private Map<String, List<Double>> sensorTemperatures;

    @PostConstruct
    public void init() {
        sensorTemperatures = new ConcurrentHashMap<>();
    }

    public void processMeasurement(Measurement measurement) {
        sensorTemperatures.compute(measurement.getUuid(), (k, v) -> v == null ? new LinkedList<>() : processNewTemperature(measurement, v));
    }

    private List<Double> processNewTemperature(Measurement measurement, List<Double> history) {
        double temperature = measurement.getTemperature();
        if (temperature <= criticalTemerature) {
            return new LinkedList<>();
        } else {
            if (history.size() < 2) {
                history.add(temperature);
                return history;
            } else {
                eventsRepository.save(new Event(measurement, Event.Type.TEMPERATURE_EXCEEDED));
                return new LinkedList<>();
            }
        }
    }

    public Collection<Event> getEventsBySensorUuid(String sensorUuid) {
        return eventsRepository.findByMeasurementUuid(sensorUuid);
    }
}
