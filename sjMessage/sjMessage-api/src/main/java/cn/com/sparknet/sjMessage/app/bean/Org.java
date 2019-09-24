package cn.com.sparknet.sjMessage.app.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Org {
	private String orgId;

	private Org parentOrg;
	private String parentOrgId;

	private String orgCode;

	private String orgName;
	private String levelName;
	private String state;
	private String choose;
	private String dbIp;
	private String dbLinkName;
	private String dbSid;
	private String simpName;
	private String ord;
	private Date createDate;
	private String ifDirectOrg;
	private List<Org> childOrgs = new ArrayList<Org>();
	private List<Dept> childDepts = new ArrayList<Dept>();

	private List<User> userManageOrg = new ArrayList<User>();

	public List<Org> getValidChildOrgs() {
		if (childOrgs == null)
			return null;
		List<Org> list = new ArrayList<Org>();
		for (int i = 0; i < childOrgs.size(); i++) {
			if (childOrgs == null)
				continue;
			Org orgEntity = childOrgs.get(i);
			if (orgEntity == null)
				continue;
			if (orgEntity.getState() != null
					&& !"-1".equals(orgEntity.getState())) {
				list.add(orgEntity);
			}
		}
		return list;
	}

	public List<Dept> getValidChildDepts() {
		if (childDepts == null)
			return null;
		List<Dept> list = new ArrayList<Dept>();
		for (int i = 0; i < childDepts.size(); i++) {
			if (childDepts == null)
				continue;
			Dept deptEntity = childDepts.get(i);
			if (deptEntity == null)
				continue;
			if (deptEntity.getState() != null
					&& !"-1".equals(deptEntity.getState())) {
				list.add(deptEntity);
			}
		}
		return list;
	}

	public List<User> getUserManageOrg() {
		return userManageOrg;
	}

	public void setUserManageOrg(List<User> userManageOrg) {
		this.userManageOrg = userManageOrg;
	}

	public Org() {
		super();
	}

	public Org(String orgId) {
		super();
		this.orgId = orgId;
	}

	public List<Dept> getChildDepts() {
		return childDepts;
	}

	public void setChildDepts(List<Dept> childDepts) {
		this.childDepts = childDepts;
	}

	public List<Org> getChildOrgs() {
		return childOrgs;
	}

	public void setChildOrgs(List<Org> childOrgs) {
		this.childOrgs = childOrgs;
	}

	public Org getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Org parentOrg) {
		this.parentOrg = parentOrg;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getDbIp() {
		return dbIp;
	}

	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	public String getDbLinkName() {
		return dbLinkName;
	}

	public void setDbLinkName(String dbLinkName) {
		this.dbLinkName = dbLinkName;
	}

	public String getDbSid() {
		return dbSid;
	}

	public void setDbSid(String dbSid) {
		this.dbSid = dbSid;
	}

	public String getSimpName() {
		return simpName;
	}

	public void setSimpName(String simpName) {
		this.simpName = simpName;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getIfDirectOrg() {
		return ifDirectOrg;
	}

	public void setIfDirectOrg(String ifDirectOrg) {
		this.ifDirectOrg = ifDirectOrg;
	}

}
