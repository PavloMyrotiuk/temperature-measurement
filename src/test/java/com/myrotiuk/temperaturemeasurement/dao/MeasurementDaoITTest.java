package com.myrotiuk.temperaturemeasurement.dao;

import com.myrotiuk.temperaturemeasurement.BaseITTest;
import com.myrotiuk.temperaturemeasurement.model.Measurement;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class MeasurementDaoITTest extends BaseITTest {

    @Autowired
    private MeasurementDao measurementDao;

    @Test
    public void shouldSaveMeasurement() {
        String uuid = "uuid";
        double temperature = 74.9;
        Date date = new Date();
        Measurement measurement = new Measurement(uuid, temperature, date);
        measurementDao.save(measurement);

        Measurement found = db().queryForObject("SELECT * FROM measurements WHERE uuid = ?", new Object[]{uuid}, (rs, rowNum) -> new Measurement(rs.getString("uuid"), rs.getDouble("temperature"), rs.getTimestamp("date")));

        assertNotNull(found);
        assertNotNull(found.getMeasurementId());
        assertThat(found.getUuid(), equalTo(uuid));
        assertThat(found.getTemperature(), equalTo(temperature));
        assertThat(found.getDate().getTime(), equalTo(date.getTime()));
    }
}
