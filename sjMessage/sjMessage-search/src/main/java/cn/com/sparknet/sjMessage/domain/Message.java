package cn.com.sparknet.sjMessage.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="message",type="t_message",shards=5,replicas=1)
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String msg_id;
	
	private String msg_type;
	
	private String receiver;
	
	private String copy_to;
	
	//@Field(type=FieldType.Text,analyzer="ik_max_word",searchAnalyzer="ik_max_word",store=false)
	private String msg_title;
	
	//@Field(type=FieldType.Text,analyzer="ik_max_word",searchAnalyzer="ik_max_word",store=false)
	private String msg_content;
	
	private String sender;
	
	private String dept_id;
	
	private String org_id;
	
	//@Field(format = DateFormat.date_time)
	private Date send_time;
	
	//@Field(format = DateFormat.date_time)
	private Date create_time;
	
	//@Field(format = DateFormat.date_time)
	private Date update_time;
	
	private String state;
	
	private String receive_name;

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCopy_to() {
		return copy_to;
	}

	public void setCopy_to(String copy_to) {
		this.copy_to = copy_to;
	}

	public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReceive_name() {
		return receive_name;
	}

	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
