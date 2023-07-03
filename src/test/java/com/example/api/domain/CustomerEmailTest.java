package com.example.api.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CustomerEmailTest {
    @Mock
    Customer customer;
    @InjectMocks
    CustomerEmail customerEmail;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuilder() throws Exception {
        CustomerEmail.CustomerEmailBuilder result = CustomerEmail.builder();
        assertNotNull(result);
    }

    @Test
    public void testCustomerEmail() throws Exception {
        CustomerEmail customerEmail = new CustomerEmail();
        customerEmail.setId(1L);
        customerEmail.setCustomer(customer);
        customerEmail.setEmail("test@example.com");

        CustomerEmail customerEmail2 = new CustomerEmail();
        customerEmail2.setId(2L);
        customerEmail2.setCustomer(customer);
        customerEmail2.setEmail("test@example.com");

        assertNotNull(customerEmail.getId());
        assertNotNull(customerEmail2.getId());
        assertEquals(customerEmail.getCustomer(), customerEmail2.getCustomer());
        assertEquals(customerEmail.getEmail(), customerEmail2.getEmail());
    }
}