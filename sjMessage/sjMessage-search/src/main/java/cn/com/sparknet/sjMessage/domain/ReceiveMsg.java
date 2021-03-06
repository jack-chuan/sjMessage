package cn.com.sparknet.sjMessage.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="receivemsg",type="t_receivemsg",shards=5,replicas=1)
public class ReceiveMsg implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String receiver;
	
	private String msg_id;
	
	private Date create_date;
	
	private String state;
	
	private String if_read;
	
	private String type;
	
	private Date update_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIf_read() {
		return if_read;
	}

	public void setIf_read(String if_read) {
		this.if_read = if_read;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
