package com.wack.controller;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import org.javatuples.Pair; 
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; 
import com.wack.model.BaseEntity;
import com.wack.service.GenericService;
import com.wack.util.Constants; 

@RestController
@RequestMapping(value = "/service/crud/{entity}")
@CrossOrigin
public class CrudEntityController extends BaseController {

	@Autowired
	@Qualifier("genericService")
	GenericService genericService;

	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BaseEntity save(@PathVariable("entity") String entity, @RequestBody BaseEntity be) {
		genericService.save(be);
		return be;
	}

	@RequestMapping(value = "/saveWithFile", method = RequestMethod.POST)
	public BaseEntity saveWithFile(@PathVariable("entity") String entity, @RequestPart("file[]") MultipartFile[] files,
			@RequestPart("dto") BaseEntity be) throws NoSuchMethodException, SecurityException, BeansException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Pair<Boolean, List<String>> results = Pair.with(true, new ArrayList());
		try {
			Class validator = this.getClass(Constants.VALIDATOR_PACKAGE_NAME + entity + "CustomValidator");
			Method aMethod = validator.getMethod("validate", BaseEntity.class);
			results = (Pair<Boolean, List<String>>) aMethod.invoke(context.getBean(validator), be);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		if (results.getValue0()) {
			this.cascadingEntities(be, be);
			this.genericService.saveWithFiles(be, Arrays.asList(files), true, Arrays.asList("fileLocation"));
		} else {
			be.setErrors(results.getValue1());
		}
		
		this.cascadingEntities(be, null);
		
		return be;
	}
	
	
	private void cascadingEntities(BaseEntity entity, BaseEntity value) {
		Field field = null;
		try {
			for (String childEntity: entity.getChildEntities()) {
				List<BaseEntity> childs = (List<BaseEntity>) entity.getClass().getMethod("get"
						+ childEntity.substring(0, 1).toUpperCase() + childEntity.substring(1)).invoke(entity);
				for (BaseEntity child : childs) {
					field = child.getClass().getDeclaredField(entity.getClass().getSimpleName().toLowerCase());
					field.setAccessible(true);
					field.set(child, value);
				}
			}
			
		} catch(Exception ex) {
			
		}
	}

}
