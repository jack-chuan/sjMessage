package cn.com.sparknet.sjMessage.app.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.sparknet.sjMessage.common.util.WaterUtil;

public class UserBean {
	private String userId;
	private String userStaffCode;
	private String personId;
	private String personName;
//	private String loginDeptId;
//	private String loginDeptName;
	private String deptId;
	private String deptName;
//	private String loginOrgId;
//	private String loginOrgName;
	private String orgId;
	private String orgName;
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
	private String dbLinkName;
	private List<String> ipAddress;
	private List<String> macAddress;
	//用户涉及部门
	private List<String> userDeptId;
	private List<String> userDeptName;
	//用户管理机构
	private List<String> userManageOrgId;
	private List<String> userManageOrgName;
	//用户管理部门
	private List<String> userManageDeptId;
	private List<String> userManageDeptName;
	//登陆部门子部门
	private List<String> childLoginDeptId;
	//登陆机构子机构
	private List<String> childLoginOrgId;
	private List<String> moduleId;
	private List<String> moduleName;
	private List<String> pdLevelId;
	private List<String> pdLevelName;
	private List<String> groupId;
	private List<String> groupName;
	
	private List<String> showOrgId;
	private List<String> showDeptId;
	private String mobile;
	private String duty;
	
	
	
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public UserBean(){
		
	}
	
	public UserBean(String userId){
		super();
		this.userId = userId;
	}
	
	public UserBean(User user){
		if(user == null)
			return ;
		this.userId = user.getUserId();
		this.userStaffCode = user.getUserStaffCode();
		this.personId = user.getPersonId();
		if(user.getDept() != null ){
			this.deptId = user.getDept().getDeptId();
			this.deptName = user.getDept().getDeptName();
		}
		if(user.getOrg() != null ){
			this.orgId = user.getOrg().getOrgId();
			this.orgName = user.getOrg().getOrgName();
			this.dbLinkName = user.getOrg().getDbLinkName();
		}
		this.userIndivId = user.getUserIndivId();
		this.createDate = user.getCreateDate();
		this.lastLoginDate = user.getLastLoginDate();
		this.state = user.getState();
		this.lapseDate = user.getLapseDate();
		this.ord = user.getOrd();
		this.userType = user.getUserType();
		this.isFreshPd = user.getIsFreshPd();
		this.isLoginIp = user.getIsLoginIp();
		this.isLoginMac = user.getIsLoginMac();
		this.userSort = user.getUserSort();
		
		if(user.getUserDept() != null){
			this.userDeptId = new ArrayList<String>();
			this.userDeptName = new ArrayList<String>();
			for(Dept userDept : user.getUserDept()){
				this.userDeptId.add(WaterUtil.NVLToStr(userDept.getDeptId(), ""));
				this.userDeptName.add(WaterUtil.NVLToStr(userDept.getDeptName(), ""));
			}
		}
		
		if(user.getUserManageDept() != null){
			this.userManageDeptId = new ArrayList<String>();
			this.userManageDeptName = new ArrayList<String>();
			for(Dept userManageDept : user.getUserManageDept()){
				this.userManageDeptId.add(WaterUtil.NVLToStr(userManageDept.getDeptId(), ""));
				this.userManageDeptName.add(WaterUtil.NVLToStr(userManageDept.getDeptName(), ""));
			}
		}
		
		if(user.getUserManageOrg() != null){
			this.userManageOrgId = new ArrayList<String>();
			this.userManageOrgName = new ArrayList<String>();
			for(Org userManageOrg : user.getUserManageOrg()){
				this.userManageOrgId.add(WaterUtil.NVLToStr(userManageOrg.getOrgId(), ""));
				this.userManageOrgName.add(WaterUtil.NVLToStr(userManageOrg.getOrgName(), ""));
			}
		}
		
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

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public List<String> getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(List<String> ipAddress) {
		this.ipAddress = ipAddress;
	}

	public List<String> getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(List<String> macAddress) {
		this.macAddress = macAddress;
	}

	public List<String> getUserDeptId() {
		return userDeptId;
	}

	public void setUserDeptId(List<String> userDeptId) {
		this.userDeptId = userDeptId;
	}

	public List<String> getUserDeptName() {
		return userDeptName;
	}

	public void setUserDeptName(List<String> userDeptName) {
		this.userDeptName = userDeptName;
	}

	public List<String> getUserManageOrgId() {
		return userManageOrgId;
	}

	public void setUserManageOrgId(List<String> userManageOrgId) {
		this.userManageOrgId = userManageOrgId;
	}

	public List<String> getUserManageOrgName() {
		return userManageOrgName;
	}

	public void setUserManageOrgName(List<String> userManageOrgName) {
		this.userManageOrgName = userManageOrgName;
	}

	public List<String> getUserManageDeptId() {
		return userManageDeptId;
	}

	public void setUserManageDeptId(List<String> userManageDeptId) {
		this.userManageDeptId = userManageDeptId;
	}

	public List<String> getUserManageDeptName() {
		return userManageDeptName;
	}

	public void setUserManageDeptName(List<String> userManageDeptName) {
		this.userManageDeptName = userManageDeptName;
	}

	public List<String> getModuleId() {
		return moduleId;
	}

	public void setModuleId(List<String> moduleId) {
		this.moduleId = moduleId;
	}

	public List<String> getModuleName() {
		return moduleName;
	}

	public void setModuleName(List<String> moduleName) {
		this.moduleName = moduleName;
	}

	public List<String> getPdLevelId() {
		return pdLevelId;
	}

	public void setPdLevelId(List<String> pdLevelId) {
		this.pdLevelId = pdLevelId;
	}

	public List<String> getPdLevelName() {
		return pdLevelName;
	}

	public void setPdLevelName(List<String> pdLevelName) {
		this.pdLevelName = pdLevelName;
	}

	public List<String> getGroupId() {
		return groupId;
	}

	public void setGroupId(List<String> groupId) {
		this.groupId = groupId;
	}

	public List<String> getGroupName() {
		return groupName;
	}

	public void setGroupName(List<String> groupName) {
		this.groupName = groupName;
	}


	public List<String> getChildLoginDeptId() {
		return childLoginDeptId;
	}

	public void setChildLoginDeptId(List<String> childLoginDeptId) {
		this.childLoginDeptId = childLoginDeptId;
	}

	public List<String> getChildLoginOrgId() {
		return childLoginOrgId;
	}

	public void setChildLoginOrgId(List<String> childLoginOrgId) {
		this.childLoginOrgId = childLoginOrgId;
	}

	public List<String> getShowOrgId() {
		return showOrgId;
	}

	public void setShowOrgId(List<String> showOrgId) {
		this.showOrgId = showOrgId;
	}

	public List<String> getShowDeptId() {
		return showDeptId;
	}

	public void setShowDeptId(List<String> showDeptId) {
		this.showDeptId = showDeptId;
	}

	public String getDbLinkName() {
		return dbLinkName;
	}

	public void setDbLinkName(String dbLinkName) {
		this.dbLinkName = dbLinkName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
