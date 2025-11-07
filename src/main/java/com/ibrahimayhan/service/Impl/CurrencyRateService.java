package com.ibrahimayhan.service.Impl;

import java.time.Instant;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibrahimayhan.dto.CurrencyApiResponse;
import com.ibrahimayhan.dto.RatesDto;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.service.ICurrencyRateService;

@Service
public class CurrencyRateService implements ICurrencyRateService{
	
    private final RestTemplate restTemplate = new RestTemplate();
    
    private static final String BASE_URL = "https://api.freecurrencyapi.com/v1";
    private static final String API_KEY  = "fca_live_hs5pUXdgeAw2yB0pwARryRB4Z9qpvjLeUwwYMcIR";

	
	
	@Override
	public RatesDto getAllRates() {

		String url = UriComponentsBuilder
		        .fromUriString(BASE_URL + "/latest")   // ✅ yeni önerilen yöntem
		        .queryParam("apikey", API_KEY)
		        .build(true)                           // encode işlemi için true
		        .toUriString();//url yi direkt de yazabilirdim tek satırda bu şekilde :String url = BASE_URL + "/latest?apikey=" + API_KEY;
									//ama bu şekilde ileriye daha uygun ve daha esnek
		
		
        try {
			
        	ResponseEntity<CurrencyApiResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<Void>(new HttpHeaders()),//İSTEK ATTIĞIM SERVİS BENDEN HEADERS İSTEMEDİĞİ İÇİN DİREKT NEW LEDİM AMA HEADERSA İHTİYACIM OLSA ÖNCE OLUŞTURUP SONRA İÇİNİ DOLDURUR SONRA RESTTEMPLATE VERİRİRDİM DİREKT
                    CurrencyApiResponse.class);//RESPONSE SINIFIM GENERİC YAPIDA OLSAYDI .CLASS YERİNE "ParameterizedTypeReference" KULLANARAK SERİALİZE ETMEM GEREKİRDİ
        	
        	CurrencyApiResponse body = response.getBody();

            // Uygulama DTO’su ile dön
            RatesDto dto = new RatesDto();
            dto.setBaseCurrency("USD");       // FreeCurrencyAPI default/base = USD
            dto.setRates(body.getData());     // {"TRY": .., "EUR": .., ...}
            dto.setFetchedAt(Instant.now());
            return dto;
		} catch (Exception e) {

			throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED," "+e.getMessage()));
		}

        
	}

	
}
