package com.myrotiuk.smartfrogassignment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue
    private long measurementId;
    private String uuid;
    private Date date;
    private double temperature;

    public Measurement() {
    }

    public Measurement(String uuid, double temperature, Date date) {
        this.uuid = uuid;
        this.date = date;
        this.temperature = temperature;
    }

    public String getUuid() {
        return uuid;
    }

    public double getTemperature() {
        return temperature;
    }

    public Date getDate() {
        return date;
    }

    public long getMeasurementId() {
        return measurementId;
    }
}
