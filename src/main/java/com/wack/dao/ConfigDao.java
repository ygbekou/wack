package com.wack.dao;

import java.util.List;

import com.wack.domain.GenericSearchCriteria;
import com.wack.model.stock.Product;

public interface ConfigDao {
	
	public List<Product> getProducts(GenericSearchCriteria searchCriteria);

}
