package com.ibrahimayhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibrahimayhan.model.GalleristCar;

public interface GalleristCarRepository extends JpaRepository<GalleristCar, Long>{
	
	boolean existsByGallerist_IdAndCar_Id(Long galleristId, Long carId);

}
