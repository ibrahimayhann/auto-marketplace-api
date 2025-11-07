package com.ibrahimayhan.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.DtoCar;
import com.ibrahimayhan.dto.DtoCarIU;
import com.ibrahimayhan.model.Car;
import com.ibrahimayhan.repository.CarRepository;
import com.ibrahimayhan.service.ICarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements ICarService{
	
	
	private final CarRepository carRepository;

	
	
	
	@Override
	public DtoCar saveCar(DtoCarIU input) {
		
		Car car=new Car();
		BeanUtils.copyProperties(input, car);
		car.setCreateTime(new Date());
		
		Car savedCar=carRepository.save(car);
		
		DtoCar dtoCar=new DtoCar();
		BeanUtils.copyProperties(savedCar, dtoCar);
		
		return dtoCar;
	}

	
	
	
	
	
	
	
}
