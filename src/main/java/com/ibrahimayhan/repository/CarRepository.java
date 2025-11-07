package com.ibrahimayhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibrahimayhan.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
