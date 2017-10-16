package com.myrotiuk.smartfrogassignment.web;

import com.google.gson.Gson;
import com.myrotiuk.smartfrogassignment.model.Measurement;
import com.myrotiuk.smartfrogassignment.service.MeasurementService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MeasurementControllerITTest extends BaseRestITTest {

    @Autowired
    private MeasurementService measurementService;

    @Test
    public void shouldReturnClientErrorWhenWrongCallbackTokenIsPassed() throws Exception {
        mvc().perform(post("/api/v1/sensors/{uuid}/measurements", "uuid-random")
                .content(temperature(45.6))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        ArgumentCaptor<Measurement> measurementArgumentCaptor = ArgumentCaptor.forClass(Measurement.class);

        verify(measurementService).processMeasurement(measurementArgumentCaptor.capture());
        Measurement measurement = measurementArgumentCaptor.getValue();

        assertThat(measurement.getTemperature(), equalTo(45.6));
    }


    @Test
    public void shouldReturnDataForSenor() throws Exception {
        String uuid = "uuid-random";
        double lastHour = 26.3;
        double last7Days = 34.3;
        double max30Days = 145.0;

        given(measurementService.avarageTemperatureLastHour(uuid)).willReturn(lastHour);
        given(measurementService.avarageTemperatureLast7Days(uuid)).willReturn(last7Days);
        given(measurementService.maxTemperatureLast30Days(uuid)).willReturn(max30Days);

        mvc().perform(get("/api/v1/sensors/{uuid}/measurements", uuid)).andExpect(status().isOk())
                .andExpect(jsonPath("$.sensorUuid", equalTo(uuid)))
                .andExpect(jsonPath("$.averageLastHour", equalTo(lastHour)))
                .andExpect(jsonPath("$.averageLast7Days", equalTo(last7Days)))
                .andExpect(jsonPath("$.maxLast30Days", equalTo(max30Days))
                );
    }


    private static String temperature(double temperature) {
        Map<String, Object> fields = new HashMap<>();
        fields.put("temperature", temperature);

        return new Gson().toJson(fields);
    }

}
