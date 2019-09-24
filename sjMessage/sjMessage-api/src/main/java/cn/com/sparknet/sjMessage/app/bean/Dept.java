package cn.com.sparknet.sjMessage.app.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dept {

	private String deptId;
	
	private Dept parentDept;
	private String parentDeptId;
	private List<Dept> childDepts = new ArrayList<Dept>();
	
	private Org belongOrg;
	private String belongOrgId;
	private List<User> users = new ArrayList<User>();
	private String deptName;
	private Date createDate;
	private String state;
	private String simpName;
	private String choose;
	private String ord;
	private String deptCode;
	private String deptStatName;
	
	private List<User> userManageDept = new ArrayList<User>();
	
	private List<User> userDept = new ArrayList<User>();
	
	public Dept(){
		super();
	}
	
	public List<Dept> getValidChildDepts() {
		if(childDepts == null)
			return null;
		List<Dept> list = new ArrayList<Dept>();
		for(int i=0; i<childDepts.size(); i++){
			if(childDepts == null)
				continue;
			Dept deptEntity = childDepts.get(i);
			if(deptEntity == null)
				continue;
			if(deptEntity.getState()!=null && !"-1".equals(deptEntity.getState())){
				list.add(deptEntity);
			}
		}
		return list;
	}
	
	public Dept(String deptId){
		super();
		this.deptId = deptId;
	}
	
	public Dept getParentDept() {
		return parentDept;
	}
	public void setParentDept(Dept parentDept) {
		this.parentDept = parentDept;
	}

	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
	public Org getBelongOrg() {
		return belongOrg;
	}
	public void setBelongOrg(Org belongOrg) {
		this.belongOrg = belongOrg;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSimpName() {
		return simpName;
	}
	public void setSimpName(String simpName) {
		this.simpName = simpName;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public List<Dept> getChildDepts() {
		return childDepts;
	}
	public void setChildDepts(List<Dept> childDepts) {
		this.childDepts = childDepts;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUserManageDept() {
		return userManageDept;
	}

	public void setUserManageDept(List<User> userManageDept) {
		this.userManageDept = userManageDept;
	}

	public List<User> getUserDept() {
		return userDept;
	}

	public void setUserDept(List<User> userDept) {
		this.userDept = userDept;
	}

	public String getBelongOrgId() {
		return belongOrgId;
	}

	public void setBelongOrgId(String belongOrgId) {
		this.belongOrgId = belongOrgId;
	}

	public String getDeptStatName() {
		return deptStatName;
	}

	public void setDeptStatName(String deptStatName) {
		this.deptStatName = deptStatName;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
}
