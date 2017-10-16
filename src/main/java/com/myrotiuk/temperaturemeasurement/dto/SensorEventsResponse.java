package com.myrotiuk.temperaturemeasurement.dto;

import java.util.Collection;

public class SensorEventsResponse {
    private final String sensorUuid;
    private final Collection<EventDto> events;

    public SensorEventsResponse(String sensorUuid, Collection<EventDto> events) {
        this.sensorUuid = sensorUuid;
        this.events = events;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }

    public Collection<EventDto> getEvents() {
        return events;
    }
}