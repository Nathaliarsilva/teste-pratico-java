package com.example.api.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class CustomerTest {
    @Mock
    Set<CustomerEmail> emails;

    @InjectMocks
    Customer customer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuilder() throws Exception {
        Customer.CustomerBuilder result = Customer.builder();
        assertNotNull(result);
    }
}
