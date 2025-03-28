package com.wack.service;

import java.util.List;

import org.hibernate.Session;
import org.javatuples.Quartet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile; 
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.User;
import com.wack.poll.PollQuestion;

@Service(value="genericService")
public interface GenericService {
	
	public BaseEntity save(BaseEntity entity);
	public BaseEntity saveWithFiles(BaseEntity entity, List<MultipartFile> files,
			boolean useId, List<String> attributeNames);
	public void delete(BaseEntity entity);
	public void deleteCascade(Class cl, Long id);
	public void delete(Class cl, List<Long> ids);
	public void deleteFile(Class cl, List<Long> ids, String fileName);
	public BaseEntity find(Class cl, Long key);
	public BaseEntity findWithChilds(Class cl, Long key);
	public BaseEntity findWithFiles(Class cl, Long key);
	public BaseEntity findWithChildsAndFiles(Class cl, Long key);
	public BaseEntity addFiles(BaseEntity entity);
	public List<BaseEntity> getAll(Class cl);
	public List<BaseEntity> getByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters, 
			String orderBy);
	public List<Object[]> getNativeByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters, 
			String orderBy, String groupBy);
	public Integer deleteByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters);
	public Session getConnection();
	public Company getCompany(String language);
	public Integer deleteNativeByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters);
	public String getHomePage(User user);
	public void cascadingEntities(BaseEntity entity, BaseEntity value);
	public BaseEntity saveCompany(BaseEntity entity, List<MultipartFile> files) throws Exception;
	
		
}
