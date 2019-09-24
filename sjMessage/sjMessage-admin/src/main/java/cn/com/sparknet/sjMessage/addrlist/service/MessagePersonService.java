package cn.com.sparknet.sjMessage.addrlist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.sparknet.sjMessage.common.util.PageUtils;
import cn.com.sparknet.sjMessage.addrlist.entity.MessagePersonEntity;
import org.apache.shiro.crypto.hash.Hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG人员表
 *
 * @author Leo
 * @email lzlrjok@gmail.com
 * @date 2019-03-27 09:16:01
 */
public interface MessagePersonService extends IService<MessagePersonEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<HashMap> selectPersonInfoByPersonId(String personId);
}

