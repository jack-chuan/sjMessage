package cn.com.sparknet.sjMessage.addrlist.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG机构表
 * 
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
@Data
@TableName("R$ORG")
public class MessageOrgEntity implements Serializable {
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

}
