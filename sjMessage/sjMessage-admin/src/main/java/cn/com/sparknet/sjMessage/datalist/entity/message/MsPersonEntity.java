package cn.com.sparknet.sjMessage.datalist.entity.message;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * MSG人员表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-14 11:28:10
 */
@TableName("R$PERSON_KTL")
public class MsPersonEntity implements Serializable {
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

	public String getPersonOfficial() {
		return personOfficial;
	}

	public void setPersonOfficial(String personOfficial) {
		this.personOfficial = personOfficial;
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
