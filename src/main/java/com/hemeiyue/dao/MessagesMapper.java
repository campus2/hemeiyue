package com.hemeiyue.dao;

import java.util.List;

import com.hemeiyue.entity.Messages;

public interface MessagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Messages record);

    int insertSelective(Messages record);

    Messages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKey(Messages record);
    
    List<Messages> findUserMessage(Integer userId);
}