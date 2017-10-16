package com.myrotiuk.temperaturemeasurement.dao;

import com.myrotiuk.temperaturemeasurement.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

@Repository
public class MeasurementDao {

    @Autowired
    private MeasurementsRepository measurementsRepository;

    public Measurement save(Measurement measurement) {
        return measurementsRepository.save(measurement);
    }

    public Collection<Measurement> lastHourMeasurements(String uuid) {
        return getMeasurementsForPeriod(uuid, 1, ChronoUnit.HOURS);
    }

    public Collection<Measurement> last7DaysMeasurements(String uuid) {
        return getMeasurementsForPeriod(uuid, 7, ChronoUnit.DAYS);
    }

    public Collection<Measurement> last30DaysMeasurements(String uuid) {
        return getMeasurementsForPeriod(uuid, 30, ChronoUnit.DAYS);
    }

    private Collection<Measurement> getMeasurementsForPeriod(String uuid, int period, ChronoUnit chronoUnit) {
        Date now = Date.from(Instant.now(Clock.systemUTC()));
        Date before = Date.from(Instant.now(Clock.systemUTC()).minus(period, chronoUnit));
        return measurementsRepository.findByUuidAndDateBetween(uuid, before, now);
    }
}
