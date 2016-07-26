package org.shiro.chapter13.dao;

import org.shiro.chapter13.entity.Role;

/**
 * Created by hanxy on 2016/7/26.
 * projectName : first_project
 * description :
 */
public interface RoleDao {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
