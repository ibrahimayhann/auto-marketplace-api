package com.ibrahimayhan.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoCar;
import com.ibrahimayhan.dto.DtoGallerist;
import com.ibrahimayhan.dto.DtoGalleristCar;
import com.ibrahimayhan.dto.DtoGalleristCarIU;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.model.Car;
import com.ibrahimayhan.model.Gallerist;
import com.ibrahimayhan.model.GalleristCar;
import com.ibrahimayhan.repository.CarRepository;
import com.ibrahimayhan.repository.GalleristCarRepository;
import com.ibrahimayhan.repository.GalleristRepository;
import com.ibrahimayhan.service.IGalleristCarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleristCarServiceImpl implements IGalleristCarService{
	
	private final GalleristCarRepository galleristCarRepository;
	private final CarRepository carRepository;
	private final GalleristRepository galleristRepository;
	
	
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU input) {

		Gallerist gallerist=galleristRepository.findById(input.getGalleristId()).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"GalleristId:"+input.getGalleristId().toString())));
		Car car=carRepository.findById(input.getCarId()).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"CarId:"+input.getCarId().toString())));
		
		GalleristCar galleristCar=new GalleristCar();
		galleristCar.setCar(car);
		galleristCar.setGallerist(gallerist);
		galleristCar.setCreateTime(new Date());
		
		try {
			GalleristCar savedGalleristCar=galleristCarRepository.save(galleristCar);
			DtoGalleristCar dtoGalleristCar=new DtoGalleristCar();
			BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);

			DtoCar dtoCar=new DtoCar();
			BeanUtils.copyProperties(car, dtoCar);
			dtoGalleristCar.setCar(dtoCar);
			
			
			DtoGallerist dtoGallerist=new DtoGallerist();
			BeanUtils.copyProperties(gallerist, dtoGallerist);
			DtoAddress dtoAddress=new DtoAddress();
			BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
			dtoGallerist.setAddress(dtoAddress);
			
			dtoGalleristCar.setGallerist(dtoGallerist);
			return dtoGalleristCar;

		} catch (Exception e) {
				throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION,e.getMessage()));
		}
		
		
		
		
	}
	
	
	

}
