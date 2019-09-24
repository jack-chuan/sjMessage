package cn.com.sparknet.sjMessage.app.entity;

public class MessageWithBLOBs extends Message {
    private String receiver;

    private String copyTo;

    private String msgContent;
    
    private String receiveName;

    private String sendScope;

    private String sendScopeName;

    public String getReceiver() { return receiver; }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getCopyTo() {
        return copyTo;
    }

    public void setCopyTo(String copyTo) {
        this.copyTo = copyTo == null ? null : copyTo.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

    public String getSendScope() { return sendScope; }

    public void setSendScope(String sendScope) { this.sendScope = sendScope; }

    public String getSendScopeName() { return sendScopeName; }

    public void setSendScopeName(String sendScopeName) { this.sendScopeName = sendScopeName; }
}