package com.ibrahimayhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibrahimayhan.model.SaledCar;

public interface SaledCarRepository extends JpaRepository<SaledCar, Long>{

}
