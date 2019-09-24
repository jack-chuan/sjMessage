package cn.com.sparknet.sjMessage.datalist.entity.message;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG机构表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:27:04
 */
@TableName("R$ORG")
public class MsMainOrgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 机构id
	 */
	@TableId
	private String orgId;
	/**
	 * 上级机构id
	 */
	private String parentOrgId;
	/**
	 * 机构代码
	 */
	private String orgCode;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 机构简称
	 */
	private String orgSimpname;
	/**
	 * 机构DBLINK
	 */
	private String orgDblink;
	/**
	 * 机构级别（编号表示）
	 */
	private Integer orgLevel;
	/**
	 * 该机构的创建时间
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgSimpname() {
		return orgSimpname;
	}

	public void setOrgSimpname(String orgSimpname) {
		this.orgSimpname = orgSimpname;
	}

	public String getOrgDblink() {
		return orgDblink;
	}

	public void setOrgDblink(String orgDblink) {
		this.orgDblink = orgDblink;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
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
