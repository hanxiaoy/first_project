package org.shiro.chapter6.service.impl;

import org.shiro.chapter6.dao.RoleDao;
import org.shiro.chapter6.dao.impl.RoleDaoImpl;
import org.shiro.chapter6.entity.Role;
import org.shiro.chapter6.service.RoleService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();


    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }


    
	public void deleteRole(Long roleId) {
		 roleDao.deleteRole(roleId);
	}

	/**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
	public void correlationPermission(Long roleId, Long... permissionId) {
		 roleDao.correlationPermissions(roleId, permissionId);
	}

	 /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
	public void unCorrelationPermission(Long roleId, Long... permisionId) {
		 roleDao.uncorrelationPermissions(roleId, permisionId);
	}



	public RoleDao getRoleDao() {
		return roleDao;
	}



	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}



}
