package org.shiro.chapter13.dao;

import org.shiro.chapter13.entity.Permission;

/**
 * Created by hanxy on 2016/7/25.
 * projectName : first_project
 * description :
 */
public interface  PermissionDao {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
