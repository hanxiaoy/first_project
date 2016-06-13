package org.shiro.chapter6.service.impl;

import org.shiro.chapter6.dao.PermissionDao;
import org.shiro.chapter6.dao.impl.PermissionDaoImpl;
import org.shiro.chapter6.entity.Permission;
import org.shiro.chapter6.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {

	 private PermissionDao permissionDao = new PermissionDaoImpl();

	 
	public Permission createPermission(Permission o) {
		return permissionDao.createPermission(o);
	}

	public void deletePermission(Long id) {
		permissionDao.deletePermission(id);
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	
	

}
