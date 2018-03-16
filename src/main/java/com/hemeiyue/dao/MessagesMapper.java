package com.hemeiyue.dao;

import com.hemeiyue.entity.Messages;

public interface MessagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Messages record);

    int insertSelective(Messages record);

    Messages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKey(Messages record);
}