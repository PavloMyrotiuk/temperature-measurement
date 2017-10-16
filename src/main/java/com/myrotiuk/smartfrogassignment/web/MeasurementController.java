package com.myrotiuk.smartfrogassignment.web;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.myrotiuk.smartfrogassignment.dto.MeasurementsResponse;
import com.myrotiuk.smartfrogassignment.model.Measurement;
import com.myrotiuk.smartfrogassignment.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping({"/api/v1/sensors"})
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @RequestMapping(method = RequestMethod.POST, value = "/{uuid}/measurements")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void acceptMeasurement(@PathVariable("uuid") String uuid, @RequestBody ObjectNode body) {
        measurementService.processMeasurement(new Measurement(uuid, body.get("temperature").doubleValue(), Date.from(Instant.now(Clock.systemUTC()))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}/measurements")
    public MeasurementsResponse getAggregatedData(@PathVariable("uuid") String uuid) {
        double maxLast30Days = measurementService.maxTemperatureLast30Days(uuid);
        double averageLast7Days = measurementService.avarageTemperatureLast7Days(uuid);
        double averageLastHour = measurementService.avarageTemperatureLastHour(uuid);
        return new MeasurementsResponse(uuid, averageLastHour, averageLast7Days, maxLast30Days);
    }
}
