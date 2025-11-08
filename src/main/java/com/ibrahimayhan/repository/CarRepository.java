package com.ibrahimayhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibrahimayhan.enums.CarStatusType;
import com.ibrahimayhan.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	
	 @Modifying
	    @Query("UPDATE Car c SET c.carStatusType = :status WHERE c.id = :id")
	    int updateCarStatusById(@Param("id") Long id, @Param("status") CarStatusType status);


}
