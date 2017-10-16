package com.myrotiuk.temperaturemeasurement.model;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private long eventId;

    private Type type;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    public Event() {
    }

    public Event(Measurement measurement, Type type) {
        this.measurement = measurement;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public long getEventId() {
        return eventId;
    }

    public enum Type {
        TEMPERATURE_EXCEEDED
    }
}
