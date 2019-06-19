package com.mapper;

import com.entity.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Long id);

    List<Organization> selectAll();

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    List<Organization> findAllWithExclude(@Param("id") Long id, @Param("parentIds") String parentIds);

    int updateParentIds(@Param("sourceParentIds") String sourceParentIds, @Param("targetParentIds") String targetParentIds);
}