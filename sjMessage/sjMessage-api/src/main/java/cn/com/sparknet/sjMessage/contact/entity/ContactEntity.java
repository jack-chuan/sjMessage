package cn.com.sparknet.sjMessage.contact.entity;

import java.io.Serializable;

/**
 * MSG联系人 contact
 *
 * @author zhanghm
 * @date 2019-05-20 09:16:01
 */
public class ContactEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 人员id
	 */
	private String personId;
	/**
	 * 人员姓名
	 */
	private String personName;
	/**
	 * 该人员所属部门
	 */
	private String deptId;
	/**
	 * 该人员所属部门
	 */
	private String deptName;
	/**
	 * 该人员所属机构
	 */
	private String orgId;
	/**
	 * 该人员职位
	 */
	private String personDuty;
	/**
	 * 机构名称
	 */
	private String orgName;

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPersonDuty() {
		return personDuty;
	}

	public void setPersonDuty(String personDuty) {
		this.personDuty = personDuty;
	}

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

	@Override
	public String toString() {
		return "ContactEntity{" +
				"personId='" + personId + '\'' +
				", personName='" + personName + '\'' +
				", deptId='" + deptId + '\'' +
				", deptName='" + deptName + '\'' +
				", orgId='" + orgId + '\'' +
				", personDuty='" + personDuty + '\'' +
				", orgName='" + orgName + '\'' +
				'}';
	}
}
