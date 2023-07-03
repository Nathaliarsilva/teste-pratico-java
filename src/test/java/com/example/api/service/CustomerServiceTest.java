package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.domain.CustomerEmail;
import com.example.api.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest{

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerService customerSer;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setName("Test");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer created = customerService.save(customer);

        assertSame(created.getName(), customer.getName());
    }

    @Test
    public void saveFailed() throws Exception {
        Customer customer = new Customer();
        customer.setName("test");

        assertThatCode(() -> customerService.save(customer));
    }

    @Test
    public void findAll() {
        PageRequest paginacao = PageRequest.of(1, 10, Sort.by("name"));
        List<Customer> customer = Arrays.asList((new Customer(Long.valueOf(1), "Name", new HashSet<CustomerEmail>(Arrays.asList(new CustomerEmail(Long.valueOf(1), null, "name@email.com")))))
                , (new Customer(Long.valueOf(2), "Teste", new HashSet<CustomerEmail>(Arrays.asList(new CustomerEmail(Long.valueOf(1), null, "teste@email.com"))))));
        Page<Customer> customerPage = new PageImpl<>(customer, paginacao, customer.size());

        when(customerRepository.findAll(paginacao)).thenReturn(customerPage);

        Page<Customer> found = customerService.findAll(1, 10, "name");

        assertEquals(customerPage.getTotalElements(), found.getTotalElements());
    }

    @Test
    public void findAllFailed() throws Exception {
        PageRequest paginacao = PageRequest.of(1, 10, Sort.by("name"));
        List<Customer> customer = Arrays.asList((new Customer(Long.valueOf(1), "Name", new HashSet<CustomerEmail>(Arrays.asList(new CustomerEmail(Long.valueOf(1), null, "name@email.com")))))
                , (new Customer(Long.valueOf(2), "Teste", new HashSet<CustomerEmail>(Arrays.asList(new CustomerEmail(Long.valueOf(1), null, "teste@email.com"))))));
        Page<Customer> customerPage = new PageImpl<>(customer, paginacao, customer.size());

        assertThatCode(() -> customerService.findAll(0, 0, "name"));
    }

    @Test
    public void findById() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer found = customerService.findById(1L).get();

        assertSame(found.getName(), customer.getName());
    }

    @Test
    public void findByIdFailed() throws Exception {
        Exception exception = new Exception();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");

        assertThatCode(() -> customerService.findById(2L).get()).as(String.valueOf(exception));
    }

    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setName("Test");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer created = customerService.update(customer);

        assertSame(created.getName(), customer.getName());
    }

    @Test
    public void updateFailed() throws Exception {
        Customer customer = new Customer();
        customer.setName("test");

        assertThatCode(() -> customerService.update(customer));
    }

    @Test
    public void deleteById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");

        Customer created = customerService.save(customer);

        assertThatCode(() -> customerService.deleteById(1L))
                .doesNotThrowAnyException();
    }

    @Test
    public void valida() {
        boolean result = customerService.valida("Name");
        assertEquals(true, result);
    }
}