package com.ibrahimayhan.service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoCar;
import com.ibrahimayhan.dto.DtoCustomer;
import com.ibrahimayhan.dto.DtoGallerist;
import com.ibrahimayhan.dto.DtoSaleResult;
import com.ibrahimayhan.dto.DtoSaledCar;
import com.ibrahimayhan.dto.DtoSaledCarIU;
import com.ibrahimayhan.enums.CarStatusType;
import com.ibrahimayhan.enums.CurrencyType;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.model.Car;
import com.ibrahimayhan.model.Customer;
import com.ibrahimayhan.model.Gallerist;
import com.ibrahimayhan.model.SaledCar;
import com.ibrahimayhan.repository.AccountRepository;
import com.ibrahimayhan.repository.CarRepository;
import com.ibrahimayhan.repository.CustomerRepository;
import com.ibrahimayhan.repository.GalleristCarRepository;
import com.ibrahimayhan.repository.GalleristRepository;
import com.ibrahimayhan.repository.SaledCarRepository;
import com.ibrahimayhan.service.ISaledCarService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaledCarServiceImpl implements ISaledCarService {
	
	
	private final CustomerRepository customerRepository;
	private final CarRepository carRepository;
	private final GalleristRepository galleristRepository;
	private final GalleristCarRepository galleristCarRepository;
	private final CurrencyRateService currencyRateService = new CurrencyRateService();
	private final SaledCarRepository saledCarRepository;
	private final AccountRepository accountRepository;
	
	
	
	BigDecimal rate= currencyRateService.getAllRates().getRates().get("TRY");
	
	
	@Transactional//önemliiiii
	private DtoSaleResult checkSale(DtoSaledCarIU input) {
		
		Long galleristId=input.getGalleristId();
		Long carId=input.getCarId();//her dk input almayalım diye burada tanımladım
		Long customerId=input.getCustomerId();
		
		
		
		Customer customer=customerRepository.findById(customerId).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"CustomerId:"+customerId.toString())));
		
		Gallerist gallerist=galleristRepository.findById(galleristId).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"GalleristId:"+galleristId.toString())));
		
		Car car=carRepository.findById(carId).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"CarId:"+carId.toString())));
		
		CarStatusType carStatus=car.getCarStatusType();
		
		if(carStatus==CarStatusType.SALED) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_IS_NOT_SALABLE));
		}
		
		boolean exists=galleristCarRepository.existsByGallerist_IdAndCar_Id(galleristId, carId);
		
		if (!exists) {
		    throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,
		        "Gallerist(" + galleristId + ") - Car(" + carId + ") ilişkisi yok"));
		}
		
		
		
		BigDecimal amount=customer.getAccount().getAmount();
		if(customer.getAccount().getCurrencyType()==CurrencyType.USD) {
			//asdasdasd
			BigDecimal amountTRY=amount.multiply(rate);
			amount=amountTRY;
		}
		
		
		BigDecimal carPrice=car.getPrice();
		if(car.getCurrencyType()==CurrencyType.USD) {
			//asdadasdada	
			BigDecimal priceTRY=carPrice.multiply(rate);
			carPrice=priceTRY;
		}
		
		
		if(amount.compareTo(carPrice)>=0){

			amount=amount.subtract(carPrice);
			Long accountId=customer.getAccount().getId();
			if(customer.getAccount().getCurrencyType()==CurrencyType.USD) {
				BigDecimal amounUsd=amount.divide(rate,2,RoundingMode.HALF_UP);
				amount=amounUsd;
				accountRepository.updateAmountById(accountId, amount);
			}
			accountRepository.updateAmountById(accountId, amount);
			
			carRepository.updateCarStatusById(carId, CarStatusType.SALED);
		}else {
			throw new BaseException(new ErrorMessage(MessageType.AMOUNT_IS_NO_ENOUGH));

		}
		
		
			
		
		SaledCar saledCar=new SaledCar();
		saledCar.setCreateTime(new Date());
		saledCar.setCar(car);
		saledCar.setCustomer(customer);
		saledCar.setGallerist(gallerist);
		
		
		
		DtoCar dtocar=new DtoCar();
		BeanUtils.copyProperties(car, dtocar);
		
		
		DtoCustomer dtoCustomer=new DtoCustomer();
		BeanUtils.copyProperties(customer, dtoCustomer);
		DtoAddress dtocustomeraddress=new DtoAddress();
		BeanUtils.copyProperties(customer.getAddress(), dtocustomeraddress);
		dtoCustomer.setAddress(dtocustomeraddress);
		
		
		DtoGallerist dtoGallerist=new DtoGallerist();
		BeanUtils.copyProperties(gallerist, dtoGallerist);
		DtoAddress dtogalleristAddress=new DtoAddress();
		BeanUtils.copyProperties(gallerist.getAddress(), dtogalleristAddress);
		dtoGallerist.setAddress(dtogalleristAddress);
		
		
		DtoSaledCar dtoSaledCar=new DtoSaledCar();
		dtoSaledCar.setCar(dtocar);
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		
		
		DtoSaleResult dtoSaleResult=new DtoSaleResult();
		dtoSaleResult.setDtoSaledCar(dtoSaledCar);
		dtoSaleResult.setSaledCar(saledCar);
		
		
		return dtoSaleResult;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	@Transactional//önemliiii
	public DtoSaledCar buy(DtoSaledCarIU input) {

		DtoSaleResult dtoSaleResult=checkSale(input);
		saledCarRepository.save(dtoSaleResult.getSaledCar());
		
		 
		 
		 
		return dtoSaleResult.getDtoSaledCar();
	}

	
}
