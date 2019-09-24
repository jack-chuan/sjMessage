package cn.com.sparknet.sjMessage.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import cn.com.sparknet.sjMessage.app.mapper.RUserMapper;
import cn.com.sparknet.sjMessage.app.service.RUserService;
@Service(value = "ruserService")
public class RUserServiceImpl implements RUserService{
	@Autowired
	private RUserMapper ruserMapper;
	@Override
	public String findUserIdByUserName(String userName) {	
		return ruserMapper.findUserIdByUserName(userName);
	}
	
	@Override
	public int queryUnReadCount(String userId) {
		int count = ruserMapper.queryUnReadCount(userId);
		return count;
	}

	@Override
	public int querySendCount(String userId) {
		int count = ruserMapper.querySendCount(userId);
		return count;
	}

	@Override
	public RUser findPasswordByUserId(String userId) {
		RUser rUser = ruserMapper.findPasswordByUserId(userId);
		return rUser;
	}

	@Override
	public String findDeptIdByUserName(String username) {
		return ruserMapper.findDeptIdByUserName(username);
	}

	@Override
	public String findDeptNameByDeptId(String deptId) {
		return ruserMapper.findDeptNameByDeptId(deptId);
	}

	@Override
	public RUser findRUserByUsername(String userName) {
		RUser rUser = ruserMapper.findRUserByUsername(userName);
		return rUser;
	}

	@Override
	public String findPersonName(String personId) {
		String personName = ruserMapper.findPersonName(personId);
		return personName;
	}

	@Override
	public String findOrgName(String orgId) {
		String orgName = ruserMapper.findOrgName(orgId);
		return orgName;
	}

	@Override
	public String findProperty(String name) {
		return ruserMapper.findProperty(name);
	}

}
