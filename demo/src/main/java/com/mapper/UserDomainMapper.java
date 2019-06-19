package com.mapper;

import com.domain.UserDomain;
import com.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDomain record);

    int insertSelective(UserDomain record);

    UserDomain selectByPrimaryKey(Integer id);

    List<UserDomain> selectSelective(UserVo userVo);

    int updateByPrimaryKeySelective(UserDomain record);

    int updateByPrimaryKey(UserDomain record);
}