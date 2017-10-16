package com.myrotiuk.smartfrogassignment;

import com.myrotiuk.smartfrogassignment.service.EventsService;
import com.myrotiuk.smartfrogassignment.service.MeasurementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class ITMocks {

    @Bean
    @Primary
    public MeasurementService measurementProcessingServiceMock(){
        return mock(MeasurementService.class);
    }

    @Bean
    @Primary
    public EventsService eventsServiceMock(){
        return mock(EventsService.class);
    }
}
