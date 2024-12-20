package com.wack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wack.domain.GenericSearchCriteria;
import com.wack.model.stock.Product;

@Service(value="configService")
public interface ConfigService {
	
	public List<Product> getProducts(GenericSearchCriteria searchCriteria);
}
