package cn.com.sparknet.sjMessage.app.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class MsgFile {
    private String fileId;

    private String msgId;

    private String fileTitle;

    private String fileFdfsAddress;

    private Date createDate;

    @ApiModelProperty(value = "是否删除状态",example = "0")
    private Short state;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle == null ? null : fileTitle.trim();
    }

    public String getFileFdfsAddress() {
        return fileFdfsAddress;
    }

    public void setFileFdfsAddress(String fileFdfsAddress) {
        this.fileFdfsAddress = fileFdfsAddress == null ? null : fileFdfsAddress.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}