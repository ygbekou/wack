package com.wack.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wack.model.BaseEntity;
import com.wack.model.stock.ContractLabor;
import com.wack.model.stock.Material;
import com.wack.model.stock.Quote;


@Service(value="quoteService")
public class QuoteServiceImpl  implements QuoteService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;
	
	
	@Transactional
	public BaseEntity save(Material material) throws Exception {		
		Material mt = null;
		Double amountToAdd = material.getTotalAmount();
						

		if (material.getId() != null) {
			mt = (Material) genericService.find(Material.class, material.getId());
			amountToAdd -= mt.getTotalAmount();
		}
		
		mt = (Material) genericService.save(material);
		Quote quote = (Quote) genericService.find(Quote.class, material.getQuote().getId());
		quote.setTotalAmount(quote.getTotalAmount() + amountToAdd);
		genericService.save(quote);
		

		return mt;
		
	}
	
	@Transactional
	public BaseEntity delete(Class cl, Long id) throws Exception {		
		Material material = null;
						

		if (id != null) {
			material = (Material) genericService.find(Material.class, id);
			Quote quote = (Quote) genericService.find(Quote.class, material.getQuote().getId());
			quote.setTotalAmount(quote.getTotalAmount() - material.getTotalAmount());
			genericService.save(quote);
			genericService.delete(material);
		}
		
		return material;
		
	}

	
	@Transactional
	public BaseEntity save(ContractLabor contractLabor, List<MultipartFile> files) throws Exception {		
		ContractLabor cl = null;
		Double amountToAdd = contractLabor.isActive() ? contractLabor.getAmount() : -1 * contractLabor.getAmount();
						
		if (contractLabor.getId() != null && contractLabor.isActive()) {
			cl = (ContractLabor) genericService.find(ContractLabor.class, contractLabor.getId());
			amountToAdd -= cl.getAmount();
		}
		
		cl = (ContractLabor) genericService.saveWithFiles(contractLabor, files, null, false, null);
		Quote quote = (Quote) genericService.find(Quote.class, contractLabor.getQuote().getId());
		quote.setTotalAmount(quote.getTotalAmount() + amountToAdd);
		genericService.save(quote);

		return cl;
		
	}
	
	@Transactional
	public BaseEntity deleteContractLabor(Class cl, Long id) throws Exception {		
		ContractLabor contractLabor = null;
						
		if (id != null) {
			contractLabor = (ContractLabor) genericService.find(ContractLabor.class, id);
			Quote quote = (Quote) genericService.find(Quote.class, contractLabor.getQuote().getId());
			quote.setTotalAmount(quote.getTotalAmount() - contractLabor.getAmount());
			genericService.save(quote);
			genericService.delete(contractLabor);
		}
		
		return contractLabor;
		
	}
}
