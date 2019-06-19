package com.service;

import com.entity.Role;
import com.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceService resourceService;

    public int createRole(Role role) {
        return roleMapper.insert(role);
    }

    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public void deleteRole(Long roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    public Role findOne(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIdList());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
