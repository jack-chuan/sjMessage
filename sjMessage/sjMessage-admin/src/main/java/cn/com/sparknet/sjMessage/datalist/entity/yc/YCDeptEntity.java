package cn.com.sparknet.sjMessage.datalist.entity.yc;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 *
 * @author yangqr
 * @email 540394155@qq.com
 * @date 2019-05-10 11:43:14
 */
@TableName("R$DEPT")
public class YCDeptEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId
    private String deptId;
    /**
     * 部门编号
     */
    private String deptCode;
    /**
     * 隶属部门ID
     */
    private String parentDeptId;
    /**
     * 隶属机构
     */
    private String belongOrgId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 0有效1无效
     */
    private Integer state;
    /**
     * 部门简称
     */
    private String simpName;
    /**
     * 选择标志(A可选X不可选)
     */
    private String choose;
    /**
     * 排序
     */
    private Integer ord;
    /**
     * $column.comments
     */
    private String deptStatName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getBelongOrgId() {
        return belongOrgId;
    }

    public void setBelongOrgId(String belongOrgId) {
        this.belongOrgId = belongOrgId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getSimpName() {
        return simpName;
    }

    public void setSimpName(String simpName) {
        this.simpName = simpName;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getDeptStatName() {
        return deptStatName;
    }

    public void setDeptStatName(String deptStatName) {
        this.deptStatName = deptStatName;
    }
}
