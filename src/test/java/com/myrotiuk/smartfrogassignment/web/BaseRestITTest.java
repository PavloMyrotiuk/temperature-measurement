package com.myrotiuk.smartfrogassignment.web;

import com.myrotiuk.smartfrogassignment.BaseITTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class BaseRestITTest extends BaseITTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    public void setContext(final WebApplicationContext context) {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    protected MockMvc mvc() {
        return mvc;
    }
}
