package com.example.api.web.rest;

import java.util.List;

import com.example.api.domain.Adress;
import com.example.api.domain.AdressDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;
	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service, CustomerService customerService) {
		this.service = service;
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size
	) {
		Page<Customer> pagedResult = service.findAll(page, size);
		return pagedResult.getContent();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@GetMapping("/name/{name}")
	public Customer findByName(@PathVariable String name) {
		return service.findByName(name)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@GetMapping("/email/{email}")
	public Customer findByEmail(@PathVariable String email) {
		return service.findByEmail(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@GetMapping("/gender/{gender}")
	public List<Customer> findByGender(@PathVariable String gender) {
		List<Customer> customers = service.findByGender(gender);
		if (customers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		} else {
			return customers;
		}
	}

	@PostMapping
	public void save(@RequestBody @Valid Customer customer) {
		service.save(customer);
	}

	@PutMapping("/{id}")
	public Customer update(@Valid @RequestBody Customer customer, @PathVariable Long id) {
		return service.update(customer, id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PostMapping("/{id}/enderecos")
	public void addAdress (@PathVariable Long id, @RequestBody @Valid AdressDTO adressDTO) {
		service.addAdress(id, adressDTO);
	}

	@GetMapping("/city/{city}")
	public List<Customer> findByCity(@PathVariable String city) {
		return customerService.getCustomerByCity(city);
	}

	@GetMapping("/state/{state}")
	public List<Customer> findByState(@PathVariable String state) {
		return customerService.getCustomerByState(state);
	}

}
