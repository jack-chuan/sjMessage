package cn.com.sparknet.sjMessage.app.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.ReceiveMsg;
import cn.com.sparknet.sjMessage.app.entity.SendMsg;
import cn.com.sparknet.sjMessage.app.mapper.MessageMapper;
import cn.com.sparknet.sjMessage.app.mapper.MsgFileMapper;
import cn.com.sparknet.sjMessage.app.mapper.ReceiveMsgMapper;
import cn.com.sparknet.sjMessage.app.mapper.SendMsgMapper;
import cn.com.sparknet.sjMessage.app.service.DraftService;
import cn.com.sparknet.sjMessage.search.domain.TrashMessage;
import cn.com.sparknet.sjMessage.search.repository.TrashMessageRepository;

@Service(value = "draftService")
public class DraftServiceImpl implements DraftService {

	@Autowired
	SendMsgMapper sendMsgMapper;
	
	@Autowired
	private ReceiveMsgMapper receiveMsgMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private MsgFileMapper msgFileMapper;
	
	@Autowired
	private TrashMessageRepository trashMessageRepository;
	
	/*
	 * 草稿箱消息列表
	 */
	public List<Map<String, Object>> queryDraftBoxList(String sender, int pageNum,
			int pageSize, String msgTitle, String receiveName, String sendName, String sDate, String eDate) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = sendMsgMapper.queryDraftBoxList(sender, msgTitle, receiveName, sendName, sDate, eDate);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if(null != list && list.size() > 0) {
			for(int i=0;i<list.size();i++) {
				Map<String, Object> map = list.get(i);
				MessageWithBLOBs message = messageMapper.selectByPrimaryKey(map.get("MSG_ID").toString());
				if(null != message.getReceiveName() && !"".equals(message.getReceiveName().toCharArray())) {
					map.put("RECEIVE_NAME", message.getReceiveName().trim());
				}else {
					map.put("RECEIVE_NAME", "");
				}
				if(null != message.getSendScopeName() && !"".equals(message.getSendScopeName().toCharArray())){
					map.put("SENDSCOPE_NAME", message.getSendScopeName().trim());
				}else {
					map.put("SENDSCOPE_NAME", "");
				}
				result.add(map);
			}
		}
		return result;
	}

	/*
	 * 草稿箱消息删除
	 */
	public int deleteDraftMsg(String id) {
		int count = 0;
		String state = "R";
		if(!"".equals(id)) {
			String ids[] = id.split(",");
			for(int i=0;i<ids.length;i++) {
				Optional<TrashMessage> result = trashMessageRepository.findById(ids[i]);
				TrashMessage message = result.get();
				message.setSrstate(state);
				trashMessageRepository.save(message);
				count = count +  sendMsgMapper.deleteSendMsg(state,ids[i]);
			}
		}
		return count;
	}

	/*
	 * 草稿箱消息保存、发送，根据sendType判断是否保存发送
	 */
	public int draftSendMsg(MessageWithBLOBs messageWithBLOBs, String fileIds, String sendType) {
		int count = 0;
		SendMsg send;
		ReceiveMsg receive;
		send = sendMsgMapper.selectByMsgId(messageWithBLOBs.getMsgId());
		if(!"".equals(sendType)) {
			if("S".equals(sendType)) {
				messageWithBLOBs.setSendTime(new Date());
				send.setType("S");
			} else {
                send.setType("T");
            }
			//默认为保存
			send.setState(sendType);
			send.setCreateDate(new Date());
			//保存消息表
			count = count + messageMapper.updateByPrimaryKey(messageWithBLOBs);
			//保存发送表
			count = count + sendMsgMapper.updateByPrimaryKey(send);
			//修改附件表msgId
			if(!"".equals(fileIds)) {
				String fileId[] = fileIds.split(",");
				for(int i=0;i<fileId.length;i++) {
					count = count + msgFileMapper.updateFileByMsgId(messageWithBLOBs.getMsgId(),fileId[i]);
				}
			}
			//当sendType为发送时，再插入接收表
			if("S".equals(sendType)) {
				String sendTo[] = messageWithBLOBs.getReceiver().split(",");
				for(int j=0;j<sendTo.length;j++) {
					List<Map<String, Object>> userList = receiveMsgMapper.querySendUserList(sendTo[j]);
					for(int k=0;k<userList.size();k++) {
						Map<String, Object> user = userList.get(k);
						receive = new ReceiveMsg();
						receive.setId(UUID.randomUUID().toString());
						receive.setReceiver(user.get("USER_ID").toString());
						receive.setMsgId(messageWithBLOBs.getMsgId());
						receive.setCreateDate(new Date());
						receive.setState("A");
						receive.setIfRead("0");
						receive.setType("R");
						count = count + receiveMsgMapper.insert(receive);
					}
				}
			}
		}
		return count;
	}

	@Override
	public Map<String, Object> queryDraftBoxListCount(String sender, int pageNum, int pageSize, String msgTitle,
			String receiveName, String sendName, String sDate, String eDate) {
		return sendMsgMapper.queryDraftBoxListCount(sender, msgTitle, receiveName, sendName, sDate, eDate);
	}

}
