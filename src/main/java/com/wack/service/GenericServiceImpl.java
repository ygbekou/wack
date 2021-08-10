package com.wack.service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.wack.dao.GenericDao;
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.User;
import com.wack.model.authorization.UserRole;
import com.wack.util.Constants;

@Service(value = "genericService")
public class GenericServiceImpl implements GenericService {

	Map<String, List<String>> entityCascades;

	@Autowired
	GenericDao<BaseEntity, String> genericDao;

	public GenericServiceImpl() {
		this.entityCascades = new HashMap<String, List<String>>();
		this.entityCascades.put("Department", Arrays.asList(new String[] { "User" }));
	}

	@Transactional
	public BaseEntity save(BaseEntity entity) {

		BaseEntity en = null;
		entity.setModDate(new Date());
		if (entity.getModifiedBy() == null) {
			entity.setModifiedBy(1L);
		}
		if (entity.getId() == null) {
			entity.setCreateDate(new Date());
			en = this.genericDao.persist(entity);
		} else {
			en = this.genericDao.merge(entity);
		}

		this.cascadingEntities(entity, entity);
		
		return en;
	}
	

	@Transactional
	public BaseEntity saveWithFiles(BaseEntity entity, List<MultipartFile> files, boolean useId,
			List<String> attributeNames) {

		this.save(entity);

		this.cascadingEntities(entity, entity);
		
		deleteRemovedFiles(entity);

		try {
		
			int i = 0;
			int fileNameIndex = 0;
			for (MultipartFile file : files) {
				String originalFileExtension = file.getOriginalFilename()
						.substring(file.getOriginalFilename().lastIndexOf("."));

				String fileName = null;
				if (entity.getClass().getSimpleName().equalsIgnoreCase("company")) {

					fileName = saveImage(file, "company", file.getOriginalFilename());

				} else {
					if (entity.getUseIdAsFileName() != null && entity.getUseIdAsFileName() == 1) {
						fileName = saveImage(file, entity.getClass().getSimpleName().toLowerCase(), entity.getId()+".jpg");
					} else {
					
						List<String> existingFileNames = this.getFiles(entity.getId(), entity.getClass().getSimpleName().toLowerCase());
						String expectingFileName = null;
						
						while (expectingFileName == null) {
							expectingFileName = entity.getClass().getSimpleName().toLowerCase() + "_" + entity.getId() + "_" + fileNameIndex
														+ originalFileExtension;
							
							if (existingFileNames.contains(expectingFileName)) {
								expectingFileName = null;
								fileNameIndex += 1;
							}
						}
						
						fileName = saveFile(file, entity.getId(), entity.getClass().getSimpleName(), expectingFileName);
					}
				}
				String fieldName = null;
				if (file.getOriginalFilename().startsWith("picture.") || (entity.getUseIdAsFileName() == 1) ) {
					fieldName = "picture";
				} else {

					fieldName = useId ? attributeNames.get(i) : file.getOriginalFilename().split("\\.")[0];
				}

				Field field = null;
				try {
					field = entity.getClass().getDeclaredField(fieldName);
				} catch (Exception ex) {
					continue;
				}

				field.setAccessible(true);
				field.set(entity, fileName);
				this.save(entity);
				i++;
				fileNameIndex++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return entity;
	}
	
	private void deleteRemovedFiles(BaseEntity entity) {

		if (entity.getRemainingFileNames() != null && entity.getFileNames() != null) {
			List<String> differences = entity.getFileNames().stream()
					.filter(element -> !entity.getRemainingFileNames().contains(element)).collect(Collectors.toList());

			for (String fileName : differences) {
				this.deleteFile(entity.getClass().getSimpleName(), entity.getId(), fileName);
			}
		}
	}


	@Transactional
	public void delete(BaseEntity entity) {
		this.genericDao.delete(entity);
	}

	@Transactional
	public void deleteCascade(String parentEntity, String entityName, Long id) {
		String query = "DELETE FROM " + entityName + " WHERE " + parentEntity + "_ID IN (SELECT " + parentEntity;
		List<String> childEntities = this.entityCascades.get(entityName);
		for (String childEntity : childEntities) {
			deleteCascade(entityName, childEntity, id);
		}
		// this.genericDao.delete(entity);
	}

	@Transactional
	public void delete(Class cl, List<Long> ids) {
		this.genericDao.delete(cl, ids);
	}

	@Transactional
	public void deleteFile(Class cl, List<Long> ids, String fileName) {
		for (Long id : ids) {
			this.deleteFile(cl.getSimpleName(), id, fileName);
		}
	}

	public BaseEntity find(Class cl, Long key) {
		return (BaseEntity) this.genericDao.find(cl, key);
	}

	public BaseEntity findWithFiles(Class cl, Long key) {
		BaseEntity entity = (BaseEntity) this.genericDao.find(cl, key);
		if (entity.getId() != null) {
			entity.setFileNames(this.getFiles(entity.getId(), entity.getClass().getSimpleName()));
		}

		return entity;
	}

	public BaseEntity addFiles(BaseEntity entity) {
		if (entity.getId() != null) {
			entity.setFileNames(this.getFiles(entity.getId(), entity.getClass().getSimpleName()));
		}

		return entity;
	}

	public List<BaseEntity> getAll(Class cl) {
		return this.genericDao.getAll(cl);
	}

	public List<BaseEntity> getByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters,
			String orderBy) {
		return this.genericDao.getByCriteria(queryStr, parameters, orderBy);
	}

	public List<Object[]> getNativeByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters,
			String orderBy, String groupBy) {
		return this.genericDao.getNativeByCriteria(queryStr, parameters, orderBy, groupBy);
	}

	public Integer deleteByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters) {
		return this.genericDao.deleteByCriteria(queryStr, parameters);
	}

	public Session getConnection() {
		return this.genericDao.getConnection();
	}

	private String saveFile(MultipartFile file, Long entityId, String entityName, String fileName) {
		if (!file.isEmpty()) {
			try {

				// transfer to upload folder
				String storageDirectory = null;
				if (entityName != null) {
					if (file.getOriginalFilename().startsWith("picture.")) {
						storageDirectory = Constants.PIC_FOLDER + File.separator + entityName.toLowerCase()
								+ (entityName.toLowerCase().endsWith("s") ? "" : "s") + File.separator + entityId;

					} else {
						storageDirectory = Constants.DOC_FOLDER + File.separator + entityName.toLowerCase()
								+ (entityName.toLowerCase().endsWith("s") ? "" : "s") + File.separator + entityId;
					}
					File dir = new File(storageDirectory);
					if (!dir.exists()) {
						dir.mkdirs();
					}

				} else {
					throw new Exception("Unknown");
				}

				File newFile = new File(storageDirectory + "/" + fileName);
				
				//if (!newFile.exists()) {
					file.transferTo(newFile);
				//}
				
				return fileName;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private String saveImage(MultipartFile file, String entityName, String fileName) {
		if (!file.isEmpty()) {
			try {

				// transfer to upload folder
				String storageDirectory = null;
				if (entityName != null) {
					storageDirectory = Constants.PIC_FOLDER + File.separator + entityName.toLowerCase()  
							+ File.separator;
					File dir = new File(storageDirectory);
					if (!dir.exists()) {
						dir.mkdirs();
					}

				} else {
					throw new Exception("Unknown");
				}

				File newFile = new File(storageDirectory + fileName);
				file.transferTo(newFile);

				return fileName;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private List<String> getFiles(Long entityId, String entityName) {
		List<String> fileNames = new ArrayList<>();
		try {

			// transfer to upload folder
			String storageDirectory = null;
			if (entityName != null) {
				storageDirectory = Constants.PIC_FOLDER + File.separator + entityName.toLowerCase()
				+ (entityName.toLowerCase().endsWith("s") ? "" : "s") + File.separator + entityId;
				File dir = new File(storageDirectory);
				if (dir.exists()) {
					File[] files = dir.listFiles();
					for (File file : files) {
						fileNames.add(file.getName());
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {

			// transfer to upload folder
			String storageDirectory = null;
			if (entityName != null) {
				storageDirectory = Constants.DOC_FOLDER + File.separator + entityName.toLowerCase()
				+ (entityName.toLowerCase().endsWith("s") ? "" : "s") + File.separator + entityId;
				File dir = new File(storageDirectory);
				if (dir.exists()) {
					File[] files = dir.listFiles();
					for (File file : files) {
						fileNames.add(file.getName());
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileNames;

	}

	private void deleteFile(String entityName, Long entityId, String fileName) {

		try {

			String storageDirectory = null;
			if (entityName != null) {
				storageDirectory = Constants.PIC_FOLDER + File.separator + entityName.toLowerCase()
				+ (entityName.toLowerCase().endsWith("s") ? "" : "s") + File.separator + entityId;
				File dir = new File(storageDirectory);
				if (dir.exists()) {
					File file = new File(storageDirectory + "/" + fileName);
					if (file.exists()) {
						file.delete();
					}
				}

			} else {
				throw new Exception("Unknown");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Company getCompany(String language) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();

		paramTupleList.add(Quartet.with("c.language = ", "language", language, "String"));
		paramTupleList.add(Quartet.with("c.status = ", "status", "0", "Integer"));
		String queryStr = "SELECT c FROM Company c WHERE 1 = 1";
		List<BaseEntity> companies = this.getByCriteria(queryStr, paramTupleList, null);
		if (companies.size() > 0) {
			return (Company) companies.get(0);
		}

		return null;

	}

	public Integer deleteNativeByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters) {
		return this.genericDao.deleteNativeByCriteria(queryStr, parameters);
	}

	public String getHomePage(User user) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();

		String queryStr = "SELECT c FROM UserRole c WHERE c.user.id=" + user.getId();
		List<UserRole> userRoles = (List) this.getByCriteria(queryStr, paramTupleList, null);
		if (userRoles != null && userRoles.size() > 0) {
			UserRole ur = (UserRole) userRoles.get(0);
			return ur.getRole().getHomePage() == null ? "/" : ur.getRole().getHomePage().getUrlPath();
		}
		return "/admin/dashboard";
	}

	public void cascadingEntities(BaseEntity entity, BaseEntity value) {
		Field field = null;
		try {
			for (String childEntity : entity.getChildEntities()) {
				List<BaseEntity> childs = (List<BaseEntity>) entity.getClass()
						.getMethod("get" + childEntity.substring(0, 1).toUpperCase() + childEntity.substring(1))
						.invoke(entity);
				for (BaseEntity child : childs) {
					try {
						field = child.getClass().getDeclaredField(entity.getClass().getSimpleName().toLowerCase());
					} catch(Exception e) {
						field = child.getClass().getDeclaredField(entity.getClass().getSimpleName().substring(0, 1).toLowerCase() 
								+ entity.getClass().getSimpleName().substring(1));
					}
					field.setAccessible(true);
					field.set(child, value);
					if (value != null) {
						this.save(child);
					} else {
						this.cascadingEntities(child, null);
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public BaseEntity findWithChilds(Class cl, Long key) {
		BaseEntity entity = (BaseEntity) this.genericDao.find(cl, key);
		this.getChilds(entity);
		return entity;
	}

	public BaseEntity findWithChildsAndFiles(Class cl, Long key) {
		BaseEntity entity = this.findWithFiles(cl, key);
		this.getChilds(entity);
		return entity;
	}

	private void getChilds(BaseEntity entity) {
		try {
			for (String childEntity : entity.getChildEntities()) {
				String childClassName = childEntity.substring(0, 1).toUpperCase()
						+ childEntity.substring(1, childEntity.length() - 1);
				Class childClass = Class.forName(entity.getClass().getPackage().getName() + "." + childClassName);

				String queryStr = "SELECT e FROM " + childClassName + " e WHERE 1 = 1";

				List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
				String entityClassName = entity.getClass().getSimpleName();
				String entityName = entityClassName.substring(0, 1).toLowerCase() + entityClassName.substring(1);
				paramTupleList.add(Quartet.with("e." + entityName + ".id = ", "parentId", entity.getId() + "", "Long"));
				List<BaseEntity> childs = this.getByCriteria(queryStr, paramTupleList, null);

				Field field = entity.getClass().getDeclaredField(childEntity);
				field.setAccessible(true);
				field.set(entity, childs);
				
				for (BaseEntity ch: childs) {
					getChilds(ch);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
