package com.springmb.mb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.springmb.mb.entity.MbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<MbUser> {
}
