package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.domain.CustomerEmail;
import com.example.api.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService custumerService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllCustomers() throws Exception {
        PageRequest paginacao = PageRequest.of(1, 10, Sort.by("name"));
        List<Customer> customer = Arrays.asList((new Customer(Long.valueOf(1), "Name", new HashSet<CustomerEmail>(Arrays.asList(new CustomerEmail(Long.valueOf(1), null, "name@email.com")))))
                , (new Customer(Long.valueOf(2), "Teste", new HashSet<CustomerEmail>(Arrays.asList(new CustomerEmail(Long.valueOf(1), null, "teste@email.com"))))));
        Page<Customer> customerPage = new PageImpl<>(customer, paginacao, customer.size());

        when(custumerService.findAll(0,1,"name")).thenReturn(customerPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);

        when(custumerService.findById(1L)).thenReturn(Optional.of(customer));

        Optional<Customer> customerOp = (custumerService.findById(1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void save() throws Exception{
        Customer customer = new Customer();
        customer.setName("Teste");

        when(custumerService.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(customer.getName()));
    }

    @Test
    public void update() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Teste");

        when(custumerService.update(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.put("/customers/1")
                       .content(mapper.writeValueAsString(customer))
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value(customer.getName()));
    }

    @Test
    public void delete() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);

        custumerService.deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/1"))
                .andExpect(status().isOk());
    }
}