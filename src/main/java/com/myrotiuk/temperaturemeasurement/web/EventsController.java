package com.myrotiuk.temperaturemeasurement.web;


import com.myrotiuk.temperaturemeasurement.dto.EventDto;
import com.myrotiuk.temperaturemeasurement.dto.SensorEventsResponse;
import com.myrotiuk.temperaturemeasurement.model.Event;
import com.myrotiuk.temperaturemeasurement.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/api/v1/sensors"})
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}/events")
    public SensorEventsResponse acceptMeasurement(@PathVariable("uuid") String uuid) {
        Collection<Event> eventsBySensorUuid = eventsService.getEventsBySensorUuid(uuid);
        List<EventDto> eventDtos = eventsBySensorUuid.stream().map(event -> new EventDto(event.getType(), event.getMeasurement().getDate(), event.getMeasurement().getTemperature())).collect(Collectors.toList());

        return new SensorEventsResponse(uuid, eventDtos);
    }

}
