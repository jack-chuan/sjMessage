package cn.com.sparknet.sjMessage.datalist.entity.message;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG部门表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-13 10:21:05
 */
@TableName("R$DEPT_KTL")
public class MsDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门id
	 */
	@TableId
	private String deptId;
	/**
	 * 上级部门id
	 */
	private String parentDeptId;
	/**
	 * 部门代码
	 */
	private String deptCode;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 部门简称
	 */
	private String deptSimpname;
	/**
	 * 该部门所属机构id
	 */
	private String orgId;
	/**
	 * 该部门的创建时间
	 */
	private Date createDate;
	/**
	 * 顺序号
	 */
	private Integer ord;
	/**
	 * 状态位 0: 有效 1: 无效
	 */
	private Integer state;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptSimpname() {
		return deptSimpname;
	}

	public void setDeptSimpname(String deptSimpname) {
		this.deptSimpname = deptSimpname;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
