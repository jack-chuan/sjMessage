package cn.com.sparknet.sjMessage.app.mapper;

import cn.com.sparknet.sjMessage.app.entity.RUser;

public interface RUserMapper {

	String findUserIdByUserName(String userName);

	int queryUnReadCount(String userId);

	int querySendCount(String userId);

	RUser findPasswordByUserId(String userId);

	String findDeptIdByUserName(String username);

	String findDeptNameByDeptId(String deptId);

	RUser findRUserByUsername(String userName);

	String findPersonName(String personId);

	String findOrgName(String orgId);

	String findProperty(String name);


}
