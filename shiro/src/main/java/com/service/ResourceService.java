package com.service;

import com.entity.Resource;
import com.mapper.ResourceMapper;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    public int createResource(Resource resource) {
        return resourceMapper.insert(resource);
    }

    public int updateResource(Resource resource) {
        return resourceMapper.updateByPrimaryKeySelective(resource);
    }

    public void deleteResource(Long resourceId) {
        resourceMapper.deleteByPrimaryKey(resourceId);
    }

    public Resource findOne(Long resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }

    public List<Resource> findAll() {
        return resourceMapper.selectAll();
    }

    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();
        for(Resource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
