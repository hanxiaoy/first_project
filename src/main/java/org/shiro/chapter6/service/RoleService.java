package org.shiro.chapter6.service;

import org.shiro.chapter6.entity.Role;

public interface RoleService {
	
	
	public Role createRole(Role role);
	
	public void deleteRole(Long roleId);
	
	
	public void correlationPermission(Long roleId, Long... permissionId);
	
	public void unCorrelationPermission(Long roleId, Long... permisionId);
}
