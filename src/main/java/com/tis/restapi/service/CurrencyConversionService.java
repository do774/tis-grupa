package com.tis.restapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyConversionService {

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public CurrencyConversionService(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public BigDecimal convertEurToUsd(BigDecimal priceEur) {
		try {
			String url = "https://api.hnb.hr/tecajn-eur/v3?valuta=USD";
			String response = restTemplate.getForObject(url, String.class);

			List<Map<String, String>> list = objectMapper.readValue(response,
					new TypeReference<List<Map<String, String>>>() {
					});

			if (list != null && !list.isEmpty()) {
				Map<String, String> exchangeRate = list.get(0);
				String middleExchangeRate = exchangeRate.get("srednji_tecaj");

				if (middleExchangeRate != null) {
					middleExchangeRate = middleExchangeRate.replace(',', '.');
					BigDecimal conversionRate = new BigDecimal(middleExchangeRate);

					return priceEur.multiply(conversionRate);
				}
			}
			return priceEur;
		} catch (Exception e) {
			//ovo bi se moglo i bolje handlatati , tipa vratiti neki responseMsg 
			return priceEur;
		}
	}
}
