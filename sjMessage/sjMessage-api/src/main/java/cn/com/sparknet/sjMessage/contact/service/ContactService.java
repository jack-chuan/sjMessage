package cn.com.sparknet.sjMessage.contact.service;

import cn.com.sparknet.sjMessage.contact.entity.ContactEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG联系人页面 contact
 *
 * @author zhanghm
 * @date 2019-05-20 09:16:01
 */
public interface ContactService extends IService<ContactEntity> {

	PageInfo selectPersonInfoByDeptId(String deptId, int pageNum, int pageSize);

	List<Map<String, Object>> selectPersonInfoByOrgId(int pageNum, int pageSize, String orgId, String deptId, String personName, String personDuty);

	Map<String, Object> selectPersonInfoByOrgIdCount(int pageNum, int pageSize, String orgId, String deptId, String personName, String personDuty);

	PageInfo<HashMap> selectAllPersonInfo(String personName, String personDuty, int pageNum, int pageSize);

}
