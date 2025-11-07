package com.ibrahimayhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibrahimayhan.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
