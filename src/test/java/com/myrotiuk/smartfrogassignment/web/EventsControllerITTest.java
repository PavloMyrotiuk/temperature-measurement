package com.myrotiuk.smartfrogassignment.web;

import com.google.common.collect.ImmutableList;
import com.myrotiuk.smartfrogassignment.model.Event;
import com.myrotiuk.smartfrogassignment.model.Measurement;
import com.myrotiuk.smartfrogassignment.service.EventsService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventsControllerITTest extends BaseRestITTest {

    @Autowired
    private EventsService eventsService;

    @Test
    public void shouldReturnEventsBySensorUuid() throws Exception {
        String uuid = "uuid-random";
        Date date = new Date();
        double temperature = 45.0;

        given(eventsService.getEventsBySensorUuid(uuid)).willReturn(ImmutableList.of(event(uuid, Event.Type.TEMPERATURE_EXCEEDED, date, temperature)));

        mvc().perform(get("/api/v1/sensors/{uuid}/events", uuid)).andExpect(status().isOk())
                .andExpect(jsonPath("$.sensorUuid", equalTo(uuid)))
                .andExpect(jsonPath("$.events", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$.events[0].type", equalTo(Event.Type.TEMPERATURE_EXCEEDED.name())))
                .andExpect(jsonPath("$.events[0].temperature", equalTo(temperature)))
                .andExpect(jsonPath("$.events[0].at", equalTo(date.toString()))
                );
    }

    private Event event(String uuid, Event.Type type, Date date, double temperature) {
        Measurement measurement = new Measurement(uuid, temperature, date);
        return new Event(measurement, type);
    }
}
