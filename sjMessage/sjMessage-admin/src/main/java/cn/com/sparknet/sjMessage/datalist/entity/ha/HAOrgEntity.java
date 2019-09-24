package cn.com.sparknet.sjMessage.datalist.entity.ha;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:44:13
 */
@TableName("R$ORG")
public class HAOrgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 机构id
	 */
	@TableId
	private String orgId;
	/**
	 * 机构id
	 */
	private String parentOrgId;
	/**
	 * 机构代码
	 */
	private String orgCode;
	/**
	 * 机构名
	 */
	private String orgName;
	/**
	 * 级别 [ 1, 省厅 ], [ 2, 地级市局 ], [ 3, 市局 ], [ 4, 县局 ], [ 5, 区县局 ], [ 6, 区局 ], [ 7, 直辖市局 ]
	 */
	private String levelName;
	/**
	 * 状态 A有效 X无效
	 */
	private Integer state;
	/**
	 * 选择标志0可选1不可选(用于信息直报点)
	 */
	private String choose;
	/**
	 * 数据库IP
	 */
	private String dbIp;
	/**
	 * DB_LINK名
	 */
	private String dbLinkName;
	/**
	 * 数据库SID
	 */
	private String dbSid;
	/**
	 * 简称
	 */
	private String simpName;
	/**
	 * 排序
	 */
	private Integer ord;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 选择标志0可选1不可选(用于新闻直报点)
	 */
	private String newChoose;
	/**
	 * 选择标志0可选,空不可选(用于直管县)
	 */
	private String ifDirectOrg;

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

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
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

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNewChoose() {
		return newChoose;
	}

	public void setNewChoose(String newChoose) {
		this.newChoose = newChoose;
	}

	public String getIfDirectOrg() {
		return ifDirectOrg;
	}

	public void setIfDirectOrg(String ifDirectOrg) {
		this.ifDirectOrg = ifDirectOrg;
	}
}
