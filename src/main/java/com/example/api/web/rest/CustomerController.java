package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	@ApiOperation(value= "Get all customers with pagination")
	public Page<Customer> findAllCustomers(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy)
	{
		return service.findAll(pageNo, pageSize, sortBy);
	}

	@GetMapping("/{id}")
	@ApiOperation(value= "Get customer by ID")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@PostMapping()
	@Transactional
	@ApiOperation(value= "Create a new customer")
	public Customer save(@Valid @RequestBody Customer customer) {
        return service.save(customer);
    }

	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value= "Update a customer by ID")
    public Customer update(@Valid @PathVariable Long id,@Valid @RequestBody Customer customer) {
        return service.update(customer);
    }

	@Transactional
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK, reason = "Deleted a customer")
	@ApiOperation(value= "Delete a customer by ID")
	public void delete(@PathVariable Long id) throws Exception {
		service.deleteById(id);
	}
}
