package cn.com.sparknet.sjMessage.app.service;

import java.util.List;

import cn.com.sparknet.sjMessage.app.entity.MsgFile;

public interface MsgFileService {
	
	/*
	 * 转发消息附件操作：将转发消息附件重新保存并传至前台
	 */
	List<MsgFile> forwardFileOpt(String msgId);
	
	/*
	 * 通用附件上传，并将上传的附件返回至前台
	 */
	MsgFile fileUpload(String path,String fileName);
	
	/*
	 * 通用附件删除（物理删除）
	 */
	int fileDetele(String fileId);
	
	/*
	 * 通用附件删除（逻辑删除）
	 */
	int updateFileStateByFileId(String fileId);

	/*
	 * 根据附件ID查询附件详情
	 */
	MsgFile selectByPrimaryKey(String fileId);
}
