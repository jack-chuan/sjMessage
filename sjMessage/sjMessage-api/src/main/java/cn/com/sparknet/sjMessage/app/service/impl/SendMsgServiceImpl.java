package cn.com.sparknet.sjMessage.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.entity.SendMsg;
import cn.com.sparknet.sjMessage.app.mapper.MessageMapper;
import cn.com.sparknet.sjMessage.app.mapper.SendMsgMapper;
import cn.com.sparknet.sjMessage.app.service.SendMsgService;
import cn.com.sparknet.sjMessage.common.util.WaterUtil;
import cn.com.sparknet.sjMessage.search.domain.TrashMessage;
import cn.com.sparknet.sjMessage.search.repository.TrashMessageRepository;

@Service(value = "sendMsgService")
public class SendMsgServiceImpl implements SendMsgService {
	
	@Autowired
	SendMsgMapper sendMsgMapper;
	
	@Autowired
	MessageMapper messageMapper;
	
	@Autowired
	TrashMessageRepository trashMessageRepository;
	
	/**
	 * 保存/发送消息
	 */
	@Override
	public void saveMsg(MessageWithBLOBs message) {
		messageMapper.insertSelective(message);
	}
	
	/**
	 * 查询发件箱列表
	 * @author duyc
	 */
	@Override
	public List<Map<String, Object>> querySendMsgList(int pageNum, int pageSize, String sender, String state, String msgTitle, String receiveName, String sendName, String sDate, String eDate) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> list = sendMsgMapper.querySendMsgList(sender, state, msgTitle, receiveName, sendName, sDate, eDate);
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

	/**
	 * 查询发件箱消息详情
	 * @author duyc
	 */
	@Override
	public MessageWithBLOBs selectByPrimaryKey(String msgId) {
		return messageMapper.selectByPrimaryKey(msgId);
	}

	/**
	 * 删除发件箱消息
	 * @param state "R"表示从发件箱>垃圾箱    "D"表示从垃圾箱删除
	 * @param msgId
	 */
	@Override
	public int deleteSendMsg(String state, String id) {
		int count = 0;
		if(!WaterUtil.isNull(id)) {
			String ids[] = id.split(",");
			for(int i=0;i<ids.length;i++) {
				Optional<TrashMessage> result1 = trashMessageRepository.findById(ids[i]);
				TrashMessage message1 = result1.get();
				message1.setSrstate(state);
				trashMessageRepository.save(message1);
				count = count + sendMsgMapper.deleteSendMsg(state, ids[i]);
			}
		}
		return count;
	}

	/**
	 * 撤回消息
	 * @param 
	 */
	@Override
	public void backMsg(String msgId) {
		messageMapper.backMsg(msgId);
	}

	@Override
	public Map<String, Object> querySendMsgListCount(int pageNum, int pageSize, String sender, String state, String msgTitle, String receiveName, String sendName, String sDate, String eDate) {
		return sendMsgMapper.querySendMsgListCount(sender, state, msgTitle, receiveName, sendName, sDate, eDate);
	}

}
