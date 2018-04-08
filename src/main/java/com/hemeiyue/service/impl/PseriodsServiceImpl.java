package com.hemeiyue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.dao.PeriodsMapper;
import com.hemeiyue.entity.Periods;
import com.hemeiyue.service.PeriodsService;

@Service("periodsService")
public class PseriodsServiceImpl implements PeriodsService {
	
	@Autowired
	private PeriodsMapper periodsMapper;

	@Override
	public int insertPeriods(Periods periods) {
		return periodsMapper.insert(periods);
	}

	@Override
	public int updatePeriods(Periods periods) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
