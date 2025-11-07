package com.ibrahimayhan.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyException<E> {

	private String path;
	
	private Date createTime;
	
	private String hostname;
	
	private E message;//mesajın gelebilceği bikaç tür olduğu için generic olarak aldık 
	
}
