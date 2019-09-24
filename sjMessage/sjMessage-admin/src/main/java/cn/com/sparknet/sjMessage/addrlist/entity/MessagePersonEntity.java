package cn.com.sparknet.sjMessage.addrlist.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG人员表
 * 
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Data
@TableName("R$PERSON")
public class MessagePersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 人员id
	 */
	@TableId
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
	 * 该人员所属机构
	 */
	private String orgId;
	/**
	 * 该人员职位
	 */
	private String personDuty;
	/**
	 * 该人员办公室
	 */
	private String personOfficial;
	/**
	 * 该人员的创建时间
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
