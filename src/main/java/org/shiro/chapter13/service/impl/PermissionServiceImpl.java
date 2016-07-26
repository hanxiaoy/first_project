package org.shiro.chapter13.service.impl;

import org.shiro.chapter13.dao.PermissionDao;
import org.shiro.chapter13.entity.Permission;
import org.shiro.chapter13.service.PermissionService;

/**
 * Created by hanxy on 2016/7/26.
 * projectName : first_project
 * description :
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao;

    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}