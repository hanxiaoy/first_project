package org.shiro.chapter6.service;

import org.shiro.chapter6.entity.Permission;

public interface PermissionService {
	
	
	
		public Permission createPermission(Permission o);
		
		public void deletePermission(Long id);
}
