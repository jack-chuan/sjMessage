package cn.com.sparknet.sjMessage.datalist.entity.sj;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员表
 * 
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:45:12
 */
@TableName("R$PERSON")
public class SJPersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 人员ID
	 */
	@TableId
	private String personId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 职务
	 */
	private String duty;
	/**
	 * 办公电话
	 */
	private String officeTel;
	/**
	 * 传真
	 */
	private String officeFax;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 家庭电话
	 */
	private String homeTel;
	/**
	 * email
	 */
	private String email;
	/**
	 * 家庭地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String zipcode;
	/**
	 * 身份证号
	 */
	private String paperCode;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出生日期
	 */
	private Date birthDate;
	/**
	 * 血型
	 */
	private String bloodtype;
	/**
	 * 最高学历
	 */
	private String degree;
	/**
	 * 专业
	 */
	private String specialty;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 状态0有效1无效
	 */
	private Integer state;
	/**
	 * 排序
	 */
	private Integer ord;
	/**
	 * 是否考勤人员:1-是，2-否
	 */
	private String attendState;
	/**
	 * 工作开始年度
	 */
	private Date startYear;
	/**
	 * 公休假天数
	 */
	private String sabbatical;
	/**
	 * 婚姻状况：1-已婚，2-未婚
	 */
	private String maritalStatus;
	/**
	 * 房间号
	 */
	private String roomnumber;

	/**
	 * 领导手机
	 */
	private String leaderPhone;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeFax() {
		return officeFax;
	}

	public void setOfficeFax(String officeFax) {
		this.officeFax = officeFax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPaperCode() {
		return paperCode;
	}

	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public String getAttendState() {
		return attendState;
	}

	public void setAttendState(String attendState) {
		this.attendState = attendState;
	}

	public Date getStartYear() {
		return startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public String getSabbatical() {
		return sabbatical;
	}

	public void setSabbatical(String sabbatical) {
		this.sabbatical = sabbatical;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }
}
