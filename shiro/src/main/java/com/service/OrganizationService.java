package com.service;

import com.entity.Organization;
import com.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    public int createOrganization(Organization organization) {
        return organizationMapper.insert(organization);
    }

    public int updateOrganization(Organization organization) {
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    public void deleteOrganization(Long organizationId) {
        organizationMapper.deleteByPrimaryKey(organizationId);
    }

    public Organization findOne(Long organizationId) {
        return organizationMapper.selectByPrimaryKey(organizationId);
    }

    public List<Organization> findAll() {
        return organizationMapper.selectAll();
    }

    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        return organizationMapper.findAllWithExclude(excludeOraganization.getId(), excludeOraganization.makeSelfAsParentIds());
    }

    public void move(Organization source, Organization target) {
        Organization updateOrganization = new Organization();
        updateOrganization.setId(source.getId());
        updateOrganization.setParentId(target.getParentId());
        updateOrganization.setParentIds(target.getParentIds());
        organizationMapper.updateByPrimaryKeySelective(updateOrganization);

        organizationMapper.updateParentIds(source.makeSelfAsParentIds(), target.makeSelfAsParentIds());
    }
}
