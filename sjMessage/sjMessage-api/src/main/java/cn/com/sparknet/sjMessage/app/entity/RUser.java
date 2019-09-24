package cn.com.sparknet.sjMessage.app.entity;

import java.util.Date;

public class RUser {
	private String userId;
	private String userName;
	private String password;
	private String salt;
	private String personId;
	private String deptId;
	private String orgId;
	private String userType;
	private String msgToken;
	private Date createDate;
	private Date lastLoginDate;
	private int loginTimes;
	private int ord;
	private String isAllowLogin;
	private int state;
	private String orgName;
	private String deptName;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getMsgToken() {
		return msgToken;
	}
	public void setMsgToken(String msgToken) {
		this.msgToken = msgToken;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public int getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public String getIsAllowLogin() {
		return isAllowLogin;
	}
	public void setIsAllowLogin(String isAllowLogin) {
		this.isAllowLogin = isAllowLogin;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "RUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", salt=" + salt
				+ ", personId=" + personId + ", deptId=" + deptId + ", orgId=" + orgId + ", userType=" + userType
				+ ", msgToken=" + msgToken + ", createDate=" + createDate + ", lastLoginDate=" + lastLoginDate
				+ ", loginTimes=" + loginTimes + ", ord=" + ord + ", isAllowLogin=" + isAllowLogin + ", state=" + state
				+ ", orgName=" + orgName + ", deptName=" + deptName + "]";
	}

}
