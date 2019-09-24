package cn.com.sparknet.sjMessage.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cn.com.sparknet.sjMessage.app.entity.MessageWithBLOBs;
import cn.com.sparknet.sjMessage.app.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.com.sparknet.sjMessage.app.mapper.ReceiveMsgMapper;
import cn.com.sparknet.sjMessage.app.mapper.SendMsgMapper;
import cn.com.sparknet.sjMessage.app.service.TrashCanService;
import cn.com.sparknet.sjMessage.common.util.WaterUtil;
import cn.com.sparknet.sjMessage.search.domain.QueryMessage;
import cn.com.sparknet.sjMessage.search.domain.TrashMessage;
import cn.com.sparknet.sjMessage.search.repository.QueryMessageRepository;
import cn.com.sparknet.sjMessage.search.repository.TrashMessageRepository;

@Service(value = "trashCanService")
public class TrashCanServiceImpl implements TrashCanService {

	
	@Autowired
	SendMsgMapper sendMsgMapper;
	
	@Autowired
	private ReceiveMsgMapper receiveMsgMapper;

    @Autowired
    MessageMapper messageMapper;
    
    @Autowired
    TrashMessageRepository trashMessageRepository;
    
    @Autowired
    QueryMessageRepository queryMessageRepository;
	
	/*
	 *  获取垃圾箱消息列表
	 */
	@Override
	public List<Map<String, Object>> queryTrashCanList(int pageNum, int pageSize, String userId, String msgTitle, String receiveName,
			String sendName, String sDate, String eDate) {
		PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = sendMsgMapper.queryTrashCanList(userId, msgTitle, receiveName, sendName, sDate, eDate);
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
                result.add(map);
            }
        }
        return result;
	}

	/*
	 *  垃圾箱删除消息
	 */
	@Override
	public int deleteTrashCan(String id) {
		int count = 0;
		String state = "D";
		if(!WaterUtil.isNull(id)) {
			String ids[] = id.split(",");
			for(int i=0;i<ids.length;i++) {
				Optional<TrashMessage> result = trashMessageRepository.findById(ids[i]);
				TrashMessage message = result.get();
				message.setSrstate(state);
				trashMessageRepository.save(message);
				count = count + receiveMsgMapper.delInBoxMsg(state, ids[i]);
				count = count + sendMsgMapper.deleteSendMsg(state, ids[i]);
			}
		}
		return count;
	}
	
	/*
	 *  垃圾箱恢复消息
	 */
	@Override
	public int removeTrashCan(String id, String type) {
		int count = 0;
		if(!WaterUtil.isNull(id)) {
            String state = "A";
            Optional<TrashMessage> result = trashMessageRepository.findById(id);
			TrashMessage message = result.get();
			message.setSrstate(state);
			trashMessageRepository.save(message);
			if("R".equals(message.getSource())) {
				Optional<QueryMessage> result1 = queryMessageRepository.findById(id);
				QueryMessage message1 = result1.get();
				message1.setRstate(state);
				queryMessageRepository.save(message1);
			}else if("T".equals(message.getSource())) {
				Optional<TrashMessage> result1 = trashMessageRepository.findById(id);
				TrashMessage message1 = result1.get();
				message1.setSrstate(state);
				trashMessageRepository.save(message1);
			}
            count = count + receiveMsgMapper.delInBoxMsg(state, id);
            if(type != "R"){
                state = type;
                count = count + sendMsgMapper.deleteSendMsg(state, id);
            }
		}
		return count;
	}

	@Override
	public Map<String, Object> queryTrashCanListCount(int pageNum, int pageSize, String userId, String msgTitle,
			String receiveName, String sendName, String sDate, String eDate) {
		return sendMsgMapper.queryTrashCanListCount(userId, msgTitle, receiveName, sendName, sDate, eDate);
	}

}
