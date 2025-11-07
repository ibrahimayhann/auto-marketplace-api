package com.ibrahimayhan.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ibrahimayhan.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	//THİS METHOD İS FOR BASEEXCEPTİON(my class)
	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex ,WebRequest request) {
		
		return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
	}
	
	
	
	//THİS METHOD İS FOR VALİDATİON ERRORS
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		Map<String, List<String>> errorsMap = new HashMap<>();
for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
			
			if(objectError instanceof FieldError fieldError) {
				
				String fieldName=fieldError.getField();
				String errorMessage=fieldError.getDefaultMessage();
				
				errorsMap.computeIfAbsent(fieldName, k->new ArrayList<>()).add(errorMessage);
				
			}else {
				String errorMessage=objectError.getDefaultMessage();
				errorsMap.computeIfAbsent("Global",k->new ArrayList<>()).add(errorMessage);
			}
			
		}
		
		
		return ResponseEntity.badRequest().body(createApiError(errorsMap, request));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	private String getHostName() {

		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return "unknown-host";
	}
	
	
	
	public <E> ApiError<E> createApiError(E message,WebRequest request){
		
		MyException<E> exception=new MyException<>();
		exception.setPath(request.getDescription(false).substring(4));
		exception.setCreateTime(new Date());
		exception.setMessage(message);
		exception.setHostname(getHostName());
		
		
		
		ApiError<E> apiError=new ApiError<>();
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());
		apiError.setException(exception);
		return apiError;
		
		
	}
	
	
	
	
	
}
