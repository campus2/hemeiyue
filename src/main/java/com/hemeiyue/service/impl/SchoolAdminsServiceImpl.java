package com.hemeiyue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemeiyue.dao.SchooladminsMapper;
import com.hemeiyue.entity.SchoolAdmins;
import com.hemeiyue.service.SchoolAdminsService;

@Service("schoolAdminService")
public class SchoolAdminsServiceImpl implements SchoolAdminsService{
	
	@Autowired
	private SchooladminsMapper schoolAdminsMapper;
	
	
	@Override
	public boolean add(SchoolAdmins schoolAdmin) {
		if(schoolAdminsMapper.insert(schoolAdmin) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modify(SchoolAdmins schoolAdmin) {
		if(schoolAdminsMapper.updateByPrimaryKeySelective(schoolAdmin) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(SchoolAdmins schoolAdmin) {
		schoolAdmin.setStatus(2);
		if(schoolAdminsMapper.updateByPrimaryKeySelective(schoolAdmin) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<SchoolAdmins> selectBySchoolId(Integer schoolId) {
		return schoolAdminsMapper.selectBySchoolId(schoolId);
	}

	@Override
	public List<SchoolAdmins> selectByParentId(Integer parenetId) {
		return schoolAdminsMapper.selectByParentId(parenetId);
	}
	
}
