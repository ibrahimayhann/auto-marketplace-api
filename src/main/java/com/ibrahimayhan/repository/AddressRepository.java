package com.ibrahimayhan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibrahimayhan.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	

}
