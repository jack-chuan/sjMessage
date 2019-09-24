package cn.com.sparknet.sjMessage.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import cn.com.sparknet.sjMessage.common.util.DateUtils;
import cn.com.sparknet.sjMessage.search.domain.QueryMessage;
import cn.com.sparknet.sjMessage.search.domain.TrashMessage;
import cn.com.sparknet.sjMessage.search.repository.QueryMessageRepository;
import cn.com.sparknet.sjMessage.search.repository.TrashMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.ReceiveMsg;
import cn.com.sparknet.sjMessage.app.entity.SendMsg;
import cn.com.sparknet.sjMessage.app.mapper.MessageMapper;
import cn.com.sparknet.sjMessage.app.mapper.MsgFileMapper;
import cn.com.sparknet.sjMessage.app.mapper.ReceiveMsgMapper;
import cn.com.sparknet.sjMessage.app.mapper.SendMsgMapper;
import cn.com.sparknet.sjMessage.app.service.ReceiveMsgService;

@Service(value = "receiveMsgService")
public class ReceiveMsgServiceImpl implements ReceiveMsgService {

	@Autowired
	private ReceiveMsgMapper receiveMsgMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private MsgFileMapper msgFileMapper;
	
	@Autowired
	private SendMsgMapper sendMsgMapper;
	
	@Autowired
	QueryMessageRepository queryMessageReporitory;
	
	@Autowired
	TrashMessageRepository trashMessageRepository;
	 
	/*
	 * 获取收件箱已读未读列表
	 */
	public List<Map<String, Object>> queryInBoxList(String state,String receiver,String ifRead,int pageNum, int pageSize, String msgTitle, String receiveName, String sendName) {
		PageHelper.startPage(pageNum, pageSize);
		return receiveMsgMapper.queryInBoxList(state, receiver, ifRead, msgTitle, receiveName, sendName);
	}
	
	/*
	 * 标记为已读未读
	 */
	public int signRead(String id,String ifRead) {
		int count = 0;
		
		if(!"".equals(id)) {
			String ids[] = id.split(",");
			for(int i=0;i<ids.length;i++) {
				//修改es数据
				Optional<QueryMessage> result = queryMessageReporitory.findById(ids[i]);
				QueryMessage message = result.get();
				message.setIf_read(ifRead);;
				queryMessageReporitory.save(message);
				count = count +  receiveMsgMapper.signRead(ids[i],ifRead);
			}
		}
		return count;
	}

	/*
	 * 收件箱消息删除
	 */
	public int delInBoxMsg(String id) {
		int count = 0;
		String state =  "R";
		if(!"".equals(id)) {
			String ids[] = id.split(",");
			for(int i=0;i<ids.length;i++) {
				//修改es数据
				Optional<QueryMessage> result = queryMessageReporitory.findById(ids[i]);
				QueryMessage message = result.get();
				message.setRstate(state);
				queryMessageReporitory.save(message);
				Optional<TrashMessage> result1 = trashMessageRepository.findById(ids[i]);
				TrashMessage message1 = result1.get();
				message1.setSrstate(state);
				trashMessageRepository.save(message1);
				
				//修改数据库数据
				count = count +  receiveMsgMapper.delInBoxMsg(state, ids[i]);
			}
		}
		return count;
	}

	/*
	 * 获取消息详情
	 */
	public Map<String, Object> getMsgDetail(String msgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		MessageWithBLOBs message = messageMapper.selectByPrimaryKey(msgId);
		//消息标题
		map.put("msgTitle", message.getMsgTitle());
		//发送人
		String sender = "";
		Map<String, Object> user = receiveMsgMapper.getUserName(message.getSender().toString());
		if(null != user && null != user.get("USERNAME") && !"".equals(user.get("USERNAME").toString())) {
			sender = user.get("USERNAME").toString();
		}
		map.put("sender", message.getSender().toString());
        String sendTime = DateUtils.format(message.getSendTime(), DateUtils.DATE_TIME_PATTERN);
		map.put("sendTime", sendTime);
		map.put("sendName", sender);
		map.put("msgType", message.getMsgType());
		//接收者Id
		map.put("receiver", message.getReceiver().trim());
		//接收者名称
		map.put("receiveName", message.getReceiveName().trim());
		//正文
		String msgContent = "";
		if(null != message.getMsgContent() && !"".equals(message.getMsgContent())) {
			msgContent = message.getMsgContent();
		}
		map.put("msgContent", msgContent);
		//发送范围Id
        map.put("sendScopeId", message.getSendScope());
        //发送范围名称
        map.put("sendScopeName", message.getSendScopeName());
		//附件列表
		List<Map<String,Object>> fileList = msgFileMapper.selectByMsgId(msgId);
		map.put("fileList", fileList);
		//日期
        String createDate = DateUtils.format(message.getCreateTime(), DateUtils.DATE_TIME_PATTERN);
		map.put("createDate", createDate);
		return map;
	}

	/*
	 * 收件箱回复消息
	 */
	public int replyMsg(MessageWithBLOBs messageWithBLOBs, String fileIds,String sendType) {
		int count = 0;
		SendMsg send;
		ReceiveMsg receive;
		send = new SendMsg();
		if(!"".equals(sendType)) {
			if("S".equals(sendType)) {
				messageWithBLOBs.setSendTime(new Date());
                send.setType("S");
			} else {
                send.setType("T");
            }
			//默认为保存
			send.setId(UUID.randomUUID().toString());
			send.setMsgId(messageWithBLOBs.getMsgId());
			send.setSender(messageWithBLOBs.getSender());
			send.setCreateDate(new Date());
			send.setState(sendType);
			//保存消息表
			count = count + messageMapper.insert(messageWithBLOBs);
			//保存发送表
			count = count + sendMsgMapper.insert(send);
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

	/*
	 * 获取收件箱已读未读列表条数
	 */
	public Map<String, Object> queryInBoxListCount(String state, String receiver, String ifRead, int pageNum,
			int pageSize, String msgTitle, String receiveName, String sendName) {
		return receiveMsgMapper.queryInBoxListCount(state, receiver, ifRead, msgTitle, receiveName, sendName);
	}
	
}
