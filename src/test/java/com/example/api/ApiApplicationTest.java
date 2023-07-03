package com.example.api;

import org.junit.Test;

public class ApiApplicationTest {
    ApiApplication apiApplication = new ApiApplication();

    @Test
    public void testMain() throws Exception {
        ApiApplication.main(new String[]{"args"});
    }
}
