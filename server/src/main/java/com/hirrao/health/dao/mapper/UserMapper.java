package com.hirrao.health.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hirrao.health.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
