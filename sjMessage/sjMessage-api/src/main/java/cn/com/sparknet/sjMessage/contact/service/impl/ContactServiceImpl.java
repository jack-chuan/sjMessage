package cn.com.sparknet.sjMessage.contact.service.impl;

import cn.com.sparknet.sjMessage.contact.entity.ContactEntity;
import cn.com.sparknet.sjMessage.contact.mapper.ContactsMapper;
import cn.com.sparknet.sjMessage.contact.service.ContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("contactService")
public class ContactServiceImpl extends ServiceImpl<ContactsMapper, ContactEntity> implements ContactService {

	@Autowired
	private ContactsMapper contactsMapper;

	@Override
	public PageInfo<HashMap> selectPersonInfoByDeptId(String deptId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<HashMap> personInfoByDeptId = contactsMapper.selectPersonInfoByDeptId(deptId);
		PageInfo<HashMap> pageInfo = new PageInfo<>(personInfoByDeptId);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> selectPersonInfoByOrgId(int pageNum, int pageSize, String orgId, String deptId, String personName, String personDuty) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String,Object>> list = contactsMapper.selectPersonInfoByOrgId(orgId, deptId, personName, personDuty);
		return list;
	}

	@Override
	public Map<String, Object> selectPersonInfoByOrgIdCount(int pageNum, int pageSize, String orgId, String deptId, String personName, String personDuty) {
		return contactsMapper.selectPersonInfoByOrgIdCount(orgId, deptId, personName, personDuty);
	}

	@Override
	public PageInfo<HashMap> selectAllPersonInfo(String personName, String personDuty, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<HashMap> personList = contactsMapper.selectAllPersonInfo(personName, personDuty);
		PageInfo<HashMap> pageInfo = new PageInfo<>(personList);
		return pageInfo;
	}
}
