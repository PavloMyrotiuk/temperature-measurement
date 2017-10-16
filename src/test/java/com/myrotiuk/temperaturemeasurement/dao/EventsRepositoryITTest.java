package com.myrotiuk.temperaturemeasurement.dao;

import com.myrotiuk.temperaturemeasurement.BaseITTest;
import com.myrotiuk.temperaturemeasurement.model.Event;
import com.myrotiuk.temperaturemeasurement.model.Measurement;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.google.common.collect.Iterables.getOnlyElement;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class EventsRepositoryITTest extends BaseITTest {

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private MeasurementDao measurementDao;

    @Test
    public void shouldSaveEvent() {
        String uuid = "uuid";
        Measurement measurement = new Measurement(uuid, 10.9, new Date());
        Measurement saved = measurementDao.save(measurement);
        Event event = new Event(saved, Event.Type.TEMPERATURE_EXCEEDED);
        eventsRepository.save(event);

        Iterable<Event> events = eventsRepository.findAll();

        Event found = getOnlyElement(events);
        assertNotNull(found.getEventId());
        assertNotNull(found.getMeasurement());
        assertThat(found.getType(), equalTo(Event.Type.TEMPERATURE_EXCEEDED));
    }
}
