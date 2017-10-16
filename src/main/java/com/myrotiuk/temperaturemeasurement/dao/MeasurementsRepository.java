package com.myrotiuk.temperaturemeasurement.dao;

import com.myrotiuk.temperaturemeasurement.model.Measurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface MeasurementsRepository extends CrudRepository<Measurement, Long> {

    Collection<Measurement> findByUuidAndDateBetween(String uuid, Date from, Date to);
}
