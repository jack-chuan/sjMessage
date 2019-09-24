package cn.com.sparknet.sjMessage.app.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {
	private String userId;
	
	private String userStaffCode;
	
	
	private String personId;
	
	private Dept dept;
	private Org org;
	
	private String deptId;
	
	private String orgId;
	
	private String userIndivId;

	private String userPassword;
	
	private Date createDate;
	
	private Date lastLoginDate;

	private String state;
	
	private Date lapseDate;
	
	private Long ord;
	
	private String userType;
	
	private String isFreshPd;
	
	private String isLoginIp;
	
	private String isLoginMac;
	
	private String userSort;
	
	private List<Dept> userDept = new ArrayList<Dept>(); 
	
	private List<Org> userManageOrg = new ArrayList<Org>(); 
	
	private List<Dept> userManageDept = new ArrayList<Dept>(); 
	
	public User(){
		
	}
	
	public User(String userId){
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserStaffCode() {
		return userStaffCode;
	}

	public void setUserStaffCode(String userStaffCode) {
		this.userStaffCode = userStaffCode;
	}


	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		dept.setUsers(null);
		this.dept = dept;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getUserIndivId() {
		return userIndivId;
	}

	public void setUserIndivId(String userIndivId) {
		this.userIndivId = userIndivId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getLapseDate() {
		return lapseDate;
	}

	public void setLapseDate(Date lapseDate) {
		this.lapseDate = lapseDate;
	}

	public Long getOrd() {
		return ord;
	}

	public void setOrd(Long ord) {
		this.ord = ord;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getIsFreshPd() {
		return isFreshPd;
	}

	public void setIsFreshPd(String isFreshPd) {
		this.isFreshPd = isFreshPd;
	}

	public String getIsLoginIp() {
		return isLoginIp;
	}

	public void setIsLoginIp(String isLoginIp) {
		this.isLoginIp = isLoginIp;
	}

	public String getIsLoginMac() {
		return isLoginMac;
	}

	public void setIsLoginMac(String isLoginMac) {
		this.isLoginMac = isLoginMac;
	}

	public String getUserSort() {
		return userSort;
	}

	public void setUserSort(String userSort) {
		this.userSort = userSort;
	}

	public List<Dept> getUserDept() {
		return userDept;
	}

	public void setUserDept(List<Dept> userDept) {
		this.userDept = userDept;
	}

	public List<Org> getUserManageOrg() {
		return userManageOrg;
	}

	public void setUserManageOrg(List<Org> userManageOrg) {
		this.userManageOrg = userManageOrg;
	}

	public List<Dept> getUserManageDept() {
		return userManageDept;
	}

	public void setUserManageDept(List<Dept> userManageDept) {
		this.userManageDept = userManageDept;
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
	
	
}
