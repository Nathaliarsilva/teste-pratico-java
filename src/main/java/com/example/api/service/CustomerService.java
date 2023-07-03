package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public Customer save(Customer customer) {
		try{
			if(this.valida(customer.getName())){
				return repository.save(customer);
			}else{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public Page<Customer> findAll(Integer pageNo, Integer pageSize, String sortBy)
	{
		try{
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			return repository.findAll(paging);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to retrieve all customers");
		}
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public Customer update(Customer customer) {
		try{
			if(this.valida(customer.getName())){
				return repository.save(customer);
			}else{
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	public void deleteById(Long id) throws Exception {
		try{
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		}
	}

	public boolean valida(String name) {
		return name.matches("[A-Z][a-z]{1,}");
	}
}
