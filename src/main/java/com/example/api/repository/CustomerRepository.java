package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Page<Customer> findAllByOrderByNameAsc(Pageable pageable);

	Optional<Customer> findCustomerByNameIgnoreCase(String name);

	Optional<Customer> findCustomerByEmailIgnoreCase(String email);

	List<Customer> findCustomerByGenderIgnoreCase(String string);

	@Query("SELECT c FROM Customer c JOIN c.adresses a WHERE LOWER(a.city) = LOWER(:city)")
	List<Customer> findCustomerByAdressCity(@Param("city") String city);

	@Query("SELECT c FROM Customer c JOIN c.adresses a WHERE LOWER(a.state) = LOWER(:state)")
	List<Customer> findCustomerByAdressState(@Param("state") String state);
}
