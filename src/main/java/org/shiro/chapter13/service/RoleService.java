package org.shiro.chapter13.service;

import org.shiro.chapter13.entity.Role;

/**
 * Created by hanxy on 2016/7/26.
 * projectName : first_project
 * description :
 */
public interface RoleService {


    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
