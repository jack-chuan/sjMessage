package cn.com.sparknet.sjMessage.search.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="trashmessage",type="doc",shards=5,replicas=1)
public class TrashMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String msg_id;
	
	@Id
	private String id;
	
	@Field(type = FieldType.Keyword,store=false)
	private String msg_title;
	
	private String msg_type;
	
	private String msg_content;
	
	private String mstate;
	
	private String srstate;
	
	@Field(type=FieldType.Date,format=DateFormat.date)
	private String create_date;
	
	private String source;
	
	private String deptname;
	
	private String orgname;
	
	private String file_count;
	
	private String sender;
	
	private String type1;
	
	private Date mtime;
	
	private Date srtime;

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public String getMstate() {
		return mstate;
	}

	public void setMstate(String mstate) {
		this.mstate = mstate;
	}

	public String getSrstate() {
		return srstate;
	}

	public void setSrstate(String srstate) {
		this.srstate = srstate;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getFile_count() {
		return file_count;
	}

	public void setFile_count(String file_count) {
		this.file_count = file_count;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Date getSrtime() {
		return srtime;
	}

	public void setSrtime(Date srtime) {
		this.srtime = srtime;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	} 

}
