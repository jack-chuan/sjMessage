package cn.com.sparknet.sjMessage.datalist.entity.message;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG用户表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:30:03
 */
@TableName("R$USER")
public class MsMainUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private String userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户密码_盐
	 */
	private String salt;
	/**
	 * 该用户对应的人员id
	 */
	private String personId;
	/**
	 * 该用户对应的部门id
	 */
	private String deptId;
	/**
	 * 该用户对应的机构id
	 */
	private String orgId;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 消息系统token
	 */
	private String msgToken;
	/**
	 * 该用户的创建时间
	 */
	private Date createDate;
	/**
	 * 该用户最后一次登陆系统的时间
	 */
	private Date lastLoginDate;
	/**
	 * 该用户的登陆次数
	 */
	private Integer loginTimes;
	/**
	 * 顺序号
	 */
	private Integer ord;
	/**
	 * 是否允许登陆
	 */
	private String isAllowLogin;
	/**
	 * 状态位 0: 有效 1: 无效
	 */
	private Integer state;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public String getIsAllowLogin() {
		return isAllowLogin;
	}

	public void setIsAllowLogin(String isAllowLogin) {
		this.isAllowLogin = isAllowLogin;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
