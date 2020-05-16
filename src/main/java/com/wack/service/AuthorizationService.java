package com.wack.service;

import java.util.List;

import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import com.wack.domain.MenuVO;
import com.wack.domain.PermissionVO;
import com.wack.model.BaseEntity;
import com.wack.model.User;
import com.wack.model.authorization.Role;


@Service(value="authorizationService")
public interface AuthorizationService {
	
	public BaseEntity saveUserRoles(User user);
	
	public BaseEntity getUserById(Long id);
	
	public BaseEntity savePermissions(Role role);
	
	public BaseEntity getRoleById(Long id);
	
	public Pair<List<MenuVO>, List<PermissionVO>> getUserResources(Long userId, String lang);
}
