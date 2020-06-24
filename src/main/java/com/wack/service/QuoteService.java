package com.wack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wack.model.BaseEntity;
import com.wack.model.stock.ContractLabor;
import com.wack.model.stock.Material;

@Service(value="quoteService")
public interface QuoteService {
	
	public BaseEntity save(Material material) throws Exception;
	
	public BaseEntity delete(Class cl, Long id) throws Exception;
	
	public BaseEntity save(ContractLabor contractLabor, List<MultipartFile> files) throws Exception;
	
	public BaseEntity deleteContractLabor(Class cl, Long id) throws Exception;
}
