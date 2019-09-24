package cn.com.sparknet.sjMessage.addrlist.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG部门表
 * 
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Data
@TableName("R$DEPT")
public class MessageDeptEntity implements Serializable {
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

}
