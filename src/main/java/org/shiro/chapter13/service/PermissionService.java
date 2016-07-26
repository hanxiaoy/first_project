package org.shiro.chapter13.service;

import org.shiro.chapter13.entity.Permission;

/**
 * Created by hanxy on 2016/7/26.
 * projectName : first_project
 * description :
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
