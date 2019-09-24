package cn.com.sparknet.sjMessage.contact.mapper;
import cn.com.sparknet.sjMessage.contact.entity.ContactEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG联系人 contact
 *
 * @author zhanghm
 * @date 2019-05-14 09:16:01
 */
@Mapper
public interface ContactsMapper extends BaseMapper<ContactEntity> {

	List<HashMap> selectPersonInfoByDeptId(String deptId);

	List<Map<String,Object>> selectPersonInfoByOrgId(@Param("orgId") String orgId, @Param("deptId") String deptId, @Param("personName") String personName, @Param("personDuty") String personDuty);

	Map<String,Object> selectPersonInfoByOrgIdCount(@Param("orgId") String orgId, @Param("deptId") String deptId, @Param("personName") String personName, @Param("personDuty") String personDuty);

	List<HashMap> selectAllPersonInfo(@Param("personName") String personName, @Param("personDuty") String personDuty);





}
