package com.example.api.service;

import java.util.List;
import java.util.Optional;

import com.example.api.domain.Adress;
import com.example.api.domain.AdressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private AdressService adressService;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, AdressService adressService) {
		this.customerRepository = customerRepository;
		this.adressService = adressService;
	}

	public Page<Customer> findAll(int page, int size) {
		return customerRepository.findAllByOrderByNameAsc(PageRequest.of(page, size));
	}

	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	public Optional<Customer> findByName(String name) {
		return customerRepository.findCustomerByNameIgnoreCase(name);
	}

	public Optional<Customer> findByEmail(String email) {
		return customerRepository.findCustomerByEmailIgnoreCase(email);
	}

	public List<Customer> findByGender(String gender) {
		return customerRepository.findCustomerByGenderIgnoreCase(gender);
	}

	@Transactional
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	@Transactional
	public Customer update(Customer customer, Long id) {

		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id can't be null");
		}

		Customer custom = customerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

		custom.setName(customer.getName());
		custom.setEmail(customer.getEmail());
		custom.setGender(customer.getGender());

		return customerRepository.save(custom);
	}

	@Transactional
	public void delete(Long id) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id can't be null");
		}

		customerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

		customerRepository.deleteById(id);
	}

	@Transactional
	public void addAdress(Long id, AdressDTO adressDTO) {
		Optional<Customer> customer = customerRepository.findById(id);
		Customer custom;

		if (customer.isPresent()) {
			Adress adress = adressService.save(adressDTO);
			custom = customer.get();
			custom.addAdress(adress);
			customerRepository.save(custom);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		}
	}

	public List<Customer> getCustomerByCity(String city) {
		return customerRepository.findCustomerByAdressCity(city);
	}

	public List<Customer> getCustomerByState(String state) {
		return customerRepository.findCustomerByAdressState(state);
	}

}
