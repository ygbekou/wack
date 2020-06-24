package com.softenza.emarket.service;

import java.util.List;

import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import com.softenza.emarket.domain.MenuVO;
import com.softenza.emarket.domain.PermissionVO;
import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.User;
import com.softenza.emarket.model.authorization.Role;


@Service(value="authorizationService")
public interface AuthorizationService {
	
	public BaseEntity saveUserRoles(User user);
	
	public BaseEntity getUserById(Long id);
	
	public BaseEntity savePermissions(Role role);
	
	public BaseEntity getRoleById(Long id);
	
	public Pair<List<MenuVO>, List<PermissionVO>> getUserResources(Long userId, String lang);
}
