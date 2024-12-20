package com.wack.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wack.domain.GenericDto;
import com.wack.domain.GenericResponse;
import com.wack.domain.GenericVO;
import com.wack.domain.SearchAttribute;
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.User;
import com.wack.model.UserDesc;
import com.wack.model.stock.Material;
import com.wack.model.stock.PrdCategory;
import com.wack.model.stock.PrdCategoryDesc;
import com.wack.model.stock.Product;
import com.wack.model.stock.ProductDesc;
import com.wack.model.stock.Purchase;
import com.wack.service.GenericService;
import com.wack.util.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/service/{entity}")
@CrossOrigin
public class GenericEntityController extends BaseController {

	@Autowired
	@Qualifier("genericService")
	GenericService genericService;

	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BaseEntity get(@PathVariable("entity") String entity, @PathVariable("id") Long id)
			throws ClassNotFoundException {
		BaseEntity result = genericService.find(this.getClass(this.convertEntity(entity)), id);
		extraCleanUp(result, "en");
		return result;
	}

	@RequestMapping(value = "withfiles/{id}", method = RequestMethod.GET)
	public BaseEntity getWithFiles(@PathVariable("entity") String entity, @PathVariable("id") Long id)
			throws ClassNotFoundException {
		BaseEntity result = genericService.findWithFiles(this.getClass(this.convertEntity(entity)), id);
		extraCleanUp(result, "en");
		return result;
	}

	@RequestMapping(value = "withChildsAndFiles/{id}", method = RequestMethod.GET)
	public BaseEntity getWithEntitiesAndFiles(@PathVariable("entity") String entity, @PathVariable("id") Long id)
			throws ClassNotFoundException {
		BaseEntity result = genericService.findWithChildsAndFiles(this.getClass(this.convertEntity(entity)), id);
		this.genericService.cascadingEntities(result, null);
		extraCleanUp(result, "en");
		return result;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<BaseEntity> getAll(@PathVariable("entity") String entity) throws ClassNotFoundException {
		List<BaseEntity> entities = genericService.getAll(this.getClass(this.convertEntity(entity)));
		for (BaseEntity obj : entities) {
			extraCleanUp(obj, null);
		}
		return entities;
	}

	@RequestMapping(value = "/allByCriteria", method = RequestMethod.POST)
	public List<BaseEntity> getAllByCriteria(@PathVariable("entity") String entity,
			@RequestBody List<String> parameters) throws ClassNotFoundException {

		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		for (String parameter : parameters) {
			String[] paramSplit = parameter.split("\\|");
			paramTupleList.add(Quartet.with(paramSplit[0], paramSplit[1], paramSplit[2], paramSplit[3]));
		}
		
		String language = getLanguage(paramTupleList);

		String queryStr = "SELECT e FROM " + this.convertEntity(entity) + " e WHERE 1 = 1";
		List<BaseEntity> entities = genericService.getByCriteria(queryStr, paramTupleList, null);
		System.out.println(entities);

		for (BaseEntity obj : entities) {
			extraCleanUp(obj, language);
		}

		return entities;
	}

	@RequestMapping(value = "/allByCriteriaAndOrderBy", method = RequestMethod.POST)
	public List<BaseEntity> getAllByCriteriaAndOrderBy(@PathVariable("entity") String entity,
			@RequestBody SearchAttribute searchAttribute) throws ClassNotFoundException {

		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		for (String parameter : searchAttribute.getParameters()) {
			String[] paramSplit = parameter.split("\\|");
			paramTupleList.add(Quartet.with(paramSplit[0], paramSplit[1], paramSplit[2], paramSplit[3]));
		}
		
		String language = getLanguage(paramTupleList);
		
		String convertedEntity = this.convertEntity(entity);
		String queryStr = "SELECT e FROM " + convertedEntity + " e WHERE 1 = 1 " + getExtraWhereClause(convertedEntity);
		List<BaseEntity> entities = genericService.getByCriteria(queryStr, paramTupleList,
				searchAttribute.getOrderBy());

		for (BaseEntity obj : entities) {
			extraCleanUp(obj, language);
		}

		return entities;
	}

	@RequestMapping(value = "/allByCriteriaAndOrderByAndFiles", method = RequestMethod.POST)
	public List<BaseEntity> getAllByCriteriaAndOrderByAnfFiles(@PathVariable("entity") String entity,
			@RequestBody SearchAttribute searchAttribute) throws ClassNotFoundException {

		List<BaseEntity> entities = getAllByCriteriaAndOrderBy(entity, searchAttribute);

		for (BaseEntity en : entities) {
			genericService.addFiles(en);
			extraCleanUp(en, null);
		}

		return entities;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BaseEntity save(@PathVariable("entity") String entity, @RequestBody GenericDto dto)
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			InstantiationException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		BaseEntity obj = (BaseEntity) mapper.readValue(
				dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/").replaceAll("&#039;", "'"),
				this.getClass(this.convertEntity(entity)));

		Pair<Boolean, List<String>> results = Pair.with(true, new ArrayList());
		try {
			Class validator = this.getClass(Constants.VALIDATOR_PACKAGE_NAME + entity + "CustomValidator");
			Method aMethod = validator.getMethod("validate", BaseEntity.class);
			results = (Pair<Boolean, List<String>>) aMethod.invoke(context.getBean(validator), obj);
		} catch (ClassNotFoundException ex) {

		}

		if (results.getValue0()) {
			genericService.save(obj);
		} else {
			obj.setErrors(results.getValue1());
		}

		extraCleanUp(obj, null);

		return obj;
	}

	private void extraCleanUp(BaseEntity obj, String language) {
		if ("PrdCategory".equals(obj.getClass().getSimpleName())) {
			PrdCategory prdCat = (PrdCategory) obj;

			if (prdCat.getPrdCategoryDescs() != null) {
				for (PrdCategoryDesc prdCatDesc : prdCat.getPrdCategoryDescs()) {
					prdCatDesc.setPrdCategory(null);
				}
			}

			if (prdCat.getParent() != null) {
				extraCleanUp(prdCat.getParent(), language);
			}

		} else if ("PrdCategoryDesc".equals(obj.getClass().getSimpleName())) {
			PrdCategoryDesc prdCatDesc = (PrdCategoryDesc) obj;
			prdCatDesc.getPrdCategory().setPrdCategoryDescs(null);

			if (prdCatDesc.getPrdCategory().getParent() != null) {
				extraCleanUp(prdCatDesc.getPrdCategory().getParent(), language);
			}
		} else if ("Product".equals(obj.getClass().getSimpleName())) {
			Product prd = (Product) obj;
			for (ProductDesc prdDesc : prd.getProductDescs()) {
				prdDesc.setProduct(null);
			}

			extraCleanUp(prd.getPrdCategory(), language);

		} else if ("ProductDesc".equals(obj.getClass().getSimpleName())) {
			ProductDesc prdDesc = (ProductDesc) obj;
			prdDesc.getProduct().setProductDescs(null);

			extraCleanUp(prdDesc.getProduct().getPrdCategory(), language);
		} else if ("Purchase".equals(obj.getClass().getSimpleName())) {
			Purchase pur = (Purchase) obj;
			for (ProductDesc prdDesc : pur.getProduct().getProductDescs()) {
				prdDesc.setProduct(null);

				if (prdDesc.getLanguage().equalsIgnoreCase(language)) {
					pur.getProduct().setName(prdDesc.getName());
				}
			}

			extraCleanUp(pur.getProduct().getPrdCategory(), language);

		} else if ("Material".equals(obj.getClass().getSimpleName())) {
			Material ma = (Material) obj;
			for (ProductDesc prdDesc : ma.getProduct().getProductDescs()) {
				prdDesc.setProduct(null);

				if (prdDesc.getLanguage().equalsIgnoreCase(language)) {
					ma.getProduct().setName(prdDesc.getName());
				}
			}

			for (PrdCategoryDesc prdCategoryDesc : ma.getProduct().getPrdCategory().getPrdCategoryDescs()) {
				if (prdCategoryDesc.getLanguage().equalsIgnoreCase(language)) {
					ma.setPrdCategoryDesc(prdCategoryDesc);
				}
			}
			
			extraCleanUp(ma.getProduct().getPrdCategory(), language);

		}
		if ("User".equals(obj.getClass().getSimpleName())) {
			User user = (User) obj;

			if (user.getUserDescs() != null) {
				for (UserDesc userDesc : user.getUserDescs()) {
					userDesc.setUser(null);
				}
			}

		} else if ("UserDesc".equals(obj.getClass().getSimpleName())) {
			UserDesc userDesc = (UserDesc) obj;
			userDesc.getUser().setUserDescs(null);
		}
	}

	@RequestMapping(value = "/saveWithFile", method = RequestMethod.POST)
	public BaseEntity saveWithFile(@PathVariable("entity") String entity, @RequestPart("file[]") MultipartFile[] files,
			@RequestPart("dto") GenericDto dto) throws JsonParseException, JsonMappingException, IOException,
			ClassNotFoundException, NoSuchMethodException, SecurityException, BeansException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		BaseEntity obj = (BaseEntity) mapper.readValue(
				dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/").replaceAll("&#039;", "'"),
				this.getClass(entity));

		Pair<Boolean, List<String>> results = Pair.with(true, new ArrayList());
		try {
			Class validator = this.getClass(Constants.VALIDATOR_PACKAGE_NAME + entity + "CustomValidator");
			Method aMethod = validator.getMethod("validate", BaseEntity.class);
			results = (Pair<Boolean, List<String>>) aMethod.invoke(context.getBean(validator), obj);
		} catch (ClassNotFoundException ex) {

		}

		if (results.getValue0()) {
			this.genericService.saveWithFiles(obj, Arrays.asList(files), true, Arrays.asList("fileLocation"));
		} else {
			obj.setErrors(results.getValue1());
		}

		return obj;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json")
	public GenericResponse delete(@PathVariable("entity") String entity, @PathVariable("id") Long id)
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		try {
			List<Long> ids = new ArrayList<>();
			ids.add(id);
			this.genericService.delete(this.getClass(entity), ids);
			return new GenericResponse("SUCCESS");
		} catch (Exception e) {
			if (e.getMessage().contains("foreign key") || e.getMessage().contains("ConstraintViolationException")) {
				return new GenericResponse("FOREIGN_KEY_FAILURE", e.getMessage());
			} else {
				return new GenericResponse("GENERIC_FAILURE", e.getMessage());
			}
		}
	}

	@RequestMapping(value = "/deletefile", method = RequestMethod.POST, produces = "application/json")
	public GenericResponse deleteFile(@PathVariable("entity") String entity, @RequestBody GenericVO vo)
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		try {
			List<Long> ids = new ArrayList<>();
			ids.add(vo.getId());
			this.genericService.deleteFile(this.getClass(entity), ids, vo.getName());
			return new GenericResponse("SUCCESS");
		} catch (Exception e) {
			if (e.getMessage().contains("foreign key") || e.getMessage().contains("ConstraintViolationException")) {
				return new GenericResponse("FOREIGN_KEY_FAILURE", e.getMessage());
			} else {
				return new GenericResponse("GENERIC_FAILURE", e.getMessage());
			}
		}
	}

	@RequestMapping(value = "/deleteCascade/{id}", method = RequestMethod.GET, produces = "application/json")
	public GenericResponse deleteCascade(@PathVariable("entity") String entity, @PathVariable("id") Long id)
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {

		this.genericService.deleteCascade(this.getClass(entity), id);

		return new GenericResponse("SUCCESS");
	}

	private String getLanguage(List<Quartet<String, String, String, String>> paramTupleList) {
		for (Quartet<String, String, String, String> quartet : paramTupleList) {
			if (quartet.getValue0().startsWith("e.language")) {
				if (quartet.getValue3().equalsIgnoreCase("noUse")) {
					paramTupleList.remove(quartet);
				}
				
				return quartet.getValue2();
			}
		}

		return null;
	}

}
