package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import com.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    public int createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userMapper.insert(user);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public void deleteUser(Long userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User findOne(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(user.getRoleIdList().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleIdList().toArray(new Long[0]));
    }

}
