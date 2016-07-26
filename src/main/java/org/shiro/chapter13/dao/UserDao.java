package org.shiro.chapter13.dao;

import org.shiro.chapter13.entity.User;

import java.util.Set;

/**
 * Created by hanxy on 2016/7/26.
 * projectName : first_project
 * description :
 */
public interface UserDao {

    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
