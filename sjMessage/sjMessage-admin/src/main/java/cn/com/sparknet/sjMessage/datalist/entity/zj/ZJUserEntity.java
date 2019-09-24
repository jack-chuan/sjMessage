package cn.com.sparknet.sjMessage.datalist.entity.zj;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:46:23
 */
@TableName("R$USER")
public class ZJUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private String userId;
	/**
	 * 工号（登陆时的账号）
	 */
	private String userStaffCode;
	/**
	 * 人员ID
	 */
	private String personId;
	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 个性化配置ID
	 */
	private String userIndivId;
	/**
	 * 用户密码
	 */
	private String userPassword;
	/**
	 * 账号创建时间
	 */
	private Date createDate;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginDate;
	/**
	 * 账号状态0有效5锁定1无效
	 */
	private Integer state;
	/**
	 * 失效时间
	 */
	private Date lapseDate;
	/**
	 * 排序
	 */
	private Integer ord;
	/**
	 * 用户类型0为单部门1为多部门
	 */
	private String userType;
	/**
	 * 是否需要刷新权限
	 */
	private String isRefreshPd;
	/**
	 * 是否绑定IP地址
	 */
	private String isLoginIp;
	/**
	 * 是否绑定MAC地址
	 */
	private String isLoginMac;
	/**
	 * 用户类别
	 */
	private String userSort;
	/**
	 * 用户USBKEY唯一标识
	 */
	private String keyId;
	/**
	 * 0允许，1不允许
	 */
	private Integer ifAllowLogin;
	/**
	 * 用户手机端设备唯一标识
	 */
	private String userToken;

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getLapseDate() {
		return lapseDate;
	}

	public void setLapseDate(Date lapseDate) {
		this.lapseDate = lapseDate;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsRefreshPd() {
		return isRefreshPd;
	}

	public void setIsRefreshPd(String isRefreshPd) {
		this.isRefreshPd = isRefreshPd;
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

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public Integer getIfAllowLogin() {
		return ifAllowLogin;
	}

	public void setIfAllowLogin(Integer ifAllowLogin) {
		this.ifAllowLogin = ifAllowLogin;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
}
