package com.myrotiuk.temperaturemeasurement;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {TemperatureMeasurementApplication.class, ITMocks.class})
@TestPropertySource(locations = "classpath:application.properties")
public abstract class BaseITTest {

    private JdbcTemplate jdbcTemplate;
    private EntityManager entityManager;

    @Autowired
    private void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected JdbcTemplate db() {
        entityManager.flush();

        return jdbcTemplate;
    }
}
