package cn.com.sparknet.sjMessage.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;

import cn.com.sparknet.sjMessage.app.entity.MsgFile;
import cn.com.sparknet.sjMessage.app.mapper.MsgFileMapper;
import cn.com.sparknet.sjMessage.app.service.MsgFileService;

@Service(value = "msgFileService")
public class MsgFileServiceImpl implements MsgFileService {

	@Autowired
	private MsgFileMapper msgFileMapper;
	
	/*
	 * 转发消息附件操作：将转发消息附件重新保存并传至前台
	 */
	public List<MsgFile> forwardFileOpt(String msgId) {
		List<String> fileIdList = new ArrayList<String>();
		List<Map<String, Object>> oldList = msgFileMapper.selectByMsgId(msgId);
		List<MsgFile> newList = new ArrayList<MsgFile>();
		if(null != oldList && oldList.size() > 0) {
			for(int i=0;i<oldList.size();i++) {
				Map<String, Object> oldFile = oldList.get(i);
				MsgFile file = new MsgFile();
				String fileId = UUID.randomUUID().toString();
				file.setFileId(fileId);
				file.setCreateDate(new Date());
				file.setFileTitle(oldFile.get("FILE_TITLE").toString());
				file.setFileFdfsAddress(oldFile.get("FILE_FDFS_ADDRESS").toString());
				file.setState(new Short("0"));
				int count = msgFileMapper.insert(file);
				if(count > 0) {
					fileIdList.add(fileId);
				}
			}
		}
		if(null != fileIdList && fileIdList.size()> 0) {
			for(int j=0;j<fileIdList.size();j++) {
				MsgFile file = msgFileMapper.selectByPrimaryKey(fileIdList.get(j));
				newList.add(file);
			}
		}
		return newList;
	}

	/*
	 * 通用附件上传，并将上传的附件返回至前台
	 */
	public MsgFile fileUpload(String path, String fileName) {
		MsgFile returnFile = null;
		MsgFile file = new MsgFile();
		String fileId = UUID.randomUUID().toString();
		file.setFileId(fileId);
		file.setCreateDate(new Date());
		file.setFileTitle(fileName);
		file.setFileFdfsAddress(path);
		file.setState(new Short("0"));
		int count = msgFileMapper.insert(file);
		if(count > 0) {
			returnFile = msgFileMapper.selectByPrimaryKey(fileId);
		}
		return returnFile;
	}

	/*
	 * 通用附件删除（物理删除）
	 */
	public int fileDetele(String fileId) {
		return msgFileMapper.deleteByPrimaryKey(fileId);
	}
	
	/*
	 * 通用附件删除（逻辑删除）
	 */
	public int updateFileStateByFileId(String fileId) {
		return msgFileMapper.updateFileStateByFileId(fileId);
	}


	/*
	 * 根据附件ID查询附件详情
	 */
	public MsgFile selectByPrimaryKey(String fileId) {
		return msgFileMapper.selectByPrimaryKey(fileId);
	}

}
