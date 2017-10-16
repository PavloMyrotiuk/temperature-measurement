package com.myrotiuk.smartfrogassignment.dto;

import com.myrotiuk.smartfrogassignment.model.Event;

import java.util.Date;

public class EventDto {
    private final Event.Type type;
    private final Date at;
    private final double temperature;

    public EventDto(Event.Type type, Date at, double temperature) {
        this.type = type;
        this.at = at;
        this.temperature = temperature;
    }

    public Event.Type getType() {
        return type;
    }

    public String getAt() {
        return at.toString();
    }

    public double getTemperature() {
        return temperature;
    }
}
