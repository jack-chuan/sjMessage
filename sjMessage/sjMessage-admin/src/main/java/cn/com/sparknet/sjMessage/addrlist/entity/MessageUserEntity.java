package cn.com.sparknet.sjMessage.addrlist.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG用户表
 * 
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Data
@TableName("R$USER")
public class MessageUserEntity implements Serializable {
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
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
//	@JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
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

}
