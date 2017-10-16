package com.myrotiuk.smartfrogassignment.dao;

import com.myrotiuk.smartfrogassignment.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EventsRepository extends CrudRepository<Event, Long> {

    Collection<Event> findByMeasurementUuid(String sensorUuid);
}
