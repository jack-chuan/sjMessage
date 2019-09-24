package cn.com.sparknet.sjMessage.datalist.service.impl;

import cn.com.sparknet.sjMessage.datalist.entity.cz.CZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.cz.CZUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HADeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HAOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HAPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.ha.HAUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.lyg.LYGUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.message.*;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nj.NJUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.nt.NTUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sj.SJUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sq.SQUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.sz.SZUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.tz.TZUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.wx.WXUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.xz.XZUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yc.YCUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.yz.YZUserEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJDeptEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJOrgEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJPersonEntity;
import cn.com.sparknet.sjMessage.datalist.entity.zj.ZJUserEntity;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZDeptService;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZOrgService;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZPersonService;
import cn.com.sparknet.sjMessage.datalist.service.cz.CZUserService;
import cn.com.sparknet.sjMessage.datalist.service.ha.HADeptService;
import cn.com.sparknet.sjMessage.datalist.service.ha.HAOrgService;
import cn.com.sparknet.sjMessage.datalist.service.ha.HAPersonService;
import cn.com.sparknet.sjMessage.datalist.service.ha.HAUserService;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGDeptService;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGOrgService;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGPersonService;
import cn.com.sparknet.sjMessage.datalist.service.lyg.LYGUserService;
import cn.com.sparknet.sjMessage.datalist.service.message.*;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJDeptService;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJOrgService;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJPersonService;
import cn.com.sparknet.sjMessage.datalist.service.nj.NJUserService;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTDeptService;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTOrgService;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTPersonService;
import cn.com.sparknet.sjMessage.datalist.service.nt.NTUserService;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJDeptService;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJOrgService;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJPersonService;
import cn.com.sparknet.sjMessage.datalist.service.sj.SJUserService;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQDeptService;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQOrgService;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQPersonService;
import cn.com.sparknet.sjMessage.datalist.service.sq.SQUserService;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZDeptService;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZOrgService;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZPersonService;
import cn.com.sparknet.sjMessage.datalist.service.sz.SZUserService;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZDeptService;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZOrgService;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZPersonService;
import cn.com.sparknet.sjMessage.datalist.service.tz.TZUserService;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXDeptService;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXOrgService;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXPersonService;
import cn.com.sparknet.sjMessage.datalist.service.wx.WXUserService;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZDeptService;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZOrgService;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZPersonService;
import cn.com.sparknet.sjMessage.datalist.service.xz.XZUserService;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCDeptService;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCOrgService;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCPersonService;
import cn.com.sparknet.sjMessage.datalist.service.yc.YCUserService;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZDeptService;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZOrgService;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZPersonService;
import cn.com.sparknet.sjMessage.datalist.service.yz.YZUserService;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJDeptService;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJOrgService;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJPersonService;
import cn.com.sparknet.sjMessage.datalist.service.zj.ZJUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class SynchronizedDataServiceImpl {

    private final static Logger logger = LoggerFactory.getLogger(SynchronizedDataServiceImpl.class);

    @Autowired
    private NJDeptService njDeptService;

    @Autowired
    private NJOrgService njOrgService;

    @Autowired
    private NJPersonService njPersonService;

    @Autowired
    private NJUserService njUserService;

    @Autowired
    private WXDeptService wxDeptService;

    @Autowired
    private WXOrgService wxOrgService;

    @Autowired
    private WXPersonService wxPersonService;

    @Autowired
    private WXUserService wxUserService;

    @Autowired
    private XZDeptService xzDeptService;

    @Autowired
    private XZOrgService xzOrgService;

    @Autowired
    private XZPersonService xzPersonService;

    @Autowired
    private XZUserService xzUserService;

    @Autowired
    private CZDeptService czDeptService;

    @Autowired
    private CZOrgService czOrgService;

    @Autowired
    private CZPersonService czPersonService;

    @Autowired
    private CZUserService czUserService;

    @Autowired
    private SZDeptService szDeptService;

    @Autowired
    private SZOrgService szOrgService;

    @Autowired
    private SZPersonService szPersonService;

    @Autowired
    private SZUserService szUserService;

    @Autowired
    private NTDeptService ntDeptService;

    @Autowired
    private NTOrgService ntOrgService;

    @Autowired
    private NTPersonService ntPersonService;

    @Autowired
    private NTUserService ntUserService;

    @Autowired
    private LYGDeptService lygDeptService;

    @Autowired
    private LYGOrgService lygOrgService;

    @Autowired
    private LYGPersonService lygPersonService;

    @Autowired
    private LYGUserService lygUserService;

    @Autowired
    private HADeptService haDeptService;

    @Autowired
    private HAOrgService haOrgService;

    @Autowired
    private HAPersonService haPersonService;

    @Autowired
    private HAUserService haUserService;

    @Autowired
    private YCDeptService ycDeptService;

    @Autowired
    private YCOrgService ycOrgService;

    @Autowired
    private YCPersonService ycPersonService;

    @Autowired
    private YCUserService ycUserService;

    @Autowired
    private YZDeptService yzDeptService;

    @Autowired
    private YZOrgService yzOrgService;

    @Autowired
    private YZPersonService yzPersonService;

    @Autowired
    private YZUserService yzUserService;

    @Autowired
    private ZJDeptService zjDeptService;

    @Autowired
    private ZJOrgService zjOrgService;

    @Autowired
    private ZJPersonService zjPersonService;

    @Autowired
    private ZJUserService zjUserService;

    @Autowired
    private TZDeptService tzDeptService;

    @Autowired
    private TZOrgService tzOrgService;

    @Autowired
    private TZPersonService tzPersonService;

    @Autowired
    private TZUserService tzUserService;

    @Autowired
    private SQDeptService sqDeptService;

    @Autowired
    private SQOrgService sqOrgService;

    @Autowired
    private SQPersonService sqPersonService;

    @Autowired
    private SQUserService sqUserService;

    @Autowired
    private SJDeptService sjDeptService;

    @Autowired
    private SJOrgService sjOrgService;

    @Autowired
    private SJPersonService sjPersonService;

    @Autowired
    private SJUserService sjUserService;

    @Autowired
    private MsDeptService msDeptService;

    @Autowired
    private MsOrgService msOrgService;

    @Autowired
    private MsPersonService msPersonService;

    @Autowired
    private MsUserService msUserService;

    @Autowired
    private MsMainDeptService msMainDeptService;

    @Autowired
    private MsMainOrgService msMainOrgService;

    @Autowired
    private MsMainPersonService msMainPersonService;

    @Autowired
    private MsMainUserService msMainUserService;

    @Transactional(transactionManager = "messageTransactionManager")
    public void dataKTL(){
        try {
            dataToKTL();
            kTLToMain();
        }  catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("同步数据失败", e);
        }
    }

    public void dataToKTL() throws Exception{
        //--------------------------------------------- 查询所有地市的信息 开始---------------------------------------------
        //***************************************** 南京查询到的信息 开始 *****************************************
        List<NJDeptEntity> njDeptInfo = njDeptService.queryAllList();
        List<NJOrgEntity> njOrgInfo = njOrgService.queryAllList();
        List<NJPersonEntity> njPersonInfo = njPersonService.queryAllList();
        List<NJUserEntity> njUserInfo = njUserService.queryAllList();
        //***************************************** 南京查询到的信息 结束 *****************************************

        //***************************************** 无锡查询到的信息 开始 *****************************************
        List<WXDeptEntity> wxDeptInfo = wxDeptService.queryAllList();
        List<WXOrgEntity> wxOrgInfo = wxOrgService.queryAllList();
        List<WXPersonEntity> wxPersonInfo = wxPersonService.queryAllList();
        List<WXUserEntity> wxUserInfo = wxUserService.queryAllList();
        //***************************************** 无锡查询到的信息 结束 *****************************************

        //***************************************** 徐州查询到的信息 开始 *****************************************
        List<XZDeptEntity> xzDeptInfo = xzDeptService.queryAllList();
        List<XZOrgEntity> xzOrgInfo = xzOrgService.queryAllList();
        List<XZPersonEntity> xzPersonInfo = xzPersonService.queryAllList();
        List<XZUserEntity> xzUserInfo = xzUserService.queryAllList();
        //***************************************** 徐州查询到的信息 结束 *****************************************

        //***************************************** 常州查询到的信息 开始 *****************************************
        List<CZDeptEntity> czDeptInfo = czDeptService.queryAllList();
        List<CZOrgEntity> czOrgInfo = czOrgService.queryAllList();
        List<CZPersonEntity> czPersonInfo = czPersonService.queryAllList();
        List<CZUserEntity> czUserInfo = czUserService.queryAllList();
        //***************************************** 常州查询到的信息 结束 *****************************************

        //***************************************** 苏州查询到的信息 开始 *****************************************
        List<SZDeptEntity> szDeptInfo = szDeptService.queryAllList();
        List<SZOrgEntity> szOrgInfo = szOrgService.queryAllList();
        List<SZPersonEntity> szPersonInfo = szPersonService.queryAllList();
        List<SZUserEntity> szUserInfo = szUserService.queryAllList();
        //***************************************** 苏州查询到的信息 结束 *****************************************

        //***************************************** 南通查询到的信息 开始 *****************************************
        List<NTDeptEntity> ntDeptInfo = ntDeptService.queryAllList();
        List<NTOrgEntity> ntOrgInfo = ntOrgService.queryAllList();
        List<NTPersonEntity> ntPersonInfo = ntPersonService.queryAllList();
        List<NTUserEntity> ntUserInfo = ntUserService.queryAllList();
        //***************************************** 南通查询到的信息 结束 *****************************************

        //***************************************** 连云港查询到的信息 开始 *****************************************
        List<LYGDeptEntity> lygDeptInfo = lygDeptService.queryAllList();
        List<LYGOrgEntity> lygOrgInfo = lygOrgService.queryAllList();
        List<LYGPersonEntity> lygPersonInfo = lygPersonService.queryAllList();
        List<LYGUserEntity> lygUserInfo = lygUserService.queryAllList();
        //***************************************** 连云港查询到的信息 结束 *****************************************

        //***************************************** 淮安查询到的信息 开始 *****************************************
        List<HADeptEntity> haDeptInfo = haDeptService.queryAllList();
        List<HAOrgEntity> haOrgInfo = haOrgService.queryAllList();
        List<HAPersonEntity> haPersonInfo = haPersonService.queryAllList();
        List<HAUserEntity> haUserInfo = haUserService.queryAllList();
        //***************************************** 淮安查询到的信息 结束 *****************************************

        //***************************************** 盐城查询到的信息 开始 *****************************************
        List<YCDeptEntity> ycDeptInfo = ycDeptService.queryAllList();
        List<YCOrgEntity> ycOrgInfo = ycOrgService.queryAllList();
        List<YCPersonEntity> ycPersonInfo = ycPersonService.queryAllList();
        List<YCUserEntity> ycUserInfo = ycUserService.queryAllList();
        //***************************************** 盐城查询到的信息 结束 *****************************************

        //***************************************** 扬州查询到的信息 开始 *****************************************
        List<YZDeptEntity> yzDeptInfo = yzDeptService.queryAllList();
        List<YZOrgEntity> yzOrgInfo = yzOrgService.queryAllList();
        List<YZPersonEntity> yzPersonInfo = yzPersonService.queryAllList();
        List<YZUserEntity> yzUserInfo = yzUserService.queryAllList();
        //***************************************** 扬州查询到的信息 结束 *****************************************

        //***************************************** 镇江查询到的信息 开始 *****************************************
        List<ZJDeptEntity> zjDeptInfo = zjDeptService.queryAllList();
        List<ZJOrgEntity> zjOrgInfo = zjOrgService.queryAllList();
        List<ZJPersonEntity> zjPersonInfo = zjPersonService.queryAllList();
        List<ZJUserEntity> zjUserInfo = zjUserService.queryAllList();
        //***************************************** 镇江查询到的信息 结束 *****************************************

        //***************************************** 泰州查询到的信息 开始 *****************************************
        List<TZDeptEntity> tzDeptInfo = tzDeptService.queryAllList();
        List<TZOrgEntity> tzOrgInfo = tzOrgService.queryAllList();
        List<TZPersonEntity> tzPersonInfo = tzPersonService.queryAllList();
        List<TZUserEntity> tzUserInfo = tzUserService.queryAllList();
        //***************************************** 泰州查询到的信息 结束 *****************************************

        //***************************************** 宿迁查询到的信息 开始 *****************************************
        List<SQDeptEntity> sqDeptInfo = sqDeptService.queryAllList();
        List<SQOrgEntity> sqOrgInfo = sqOrgService.queryAllList();
        List<SQPersonEntity> sqPersonInfo = sqPersonService.queryAllList();
        List<SQUserEntity> sqUserInfo = sqUserService.queryAllList();
        //***************************************** 宿迁查询到的信息 结束 *****************************************

        //***************************************** 省局查询到的信息 开始 *****************************************
        List<SJDeptEntity> sjDeptInfo = sjDeptService.queryAllList();
//        List<SJOrgEntity> sjOrgInfo = sjOrgService.queryAllList();
        List<SJPersonEntity> sjPersonInfo = sjPersonService.queryAllList();
        List<SJUserEntity> sjUserInfo = sjUserService.queryAllList();
        //***************************************** 省局查询到的信息 结束 *****************************************
        //--------------------------------------------- 查询所有地市的信息 结束---------------------------------------------

        //--------------------------------------------- 同一个事务中 开始---------------------------------------------
        //***************************************** 删除同步表的信息 开始 *****************************************
        int deleteDeptCount = msDeptService.deleteAll();
        logger.info("删除同步表部门[{}]条记录",deleteDeptCount);
        int deleteOrgCount = msOrgService.deleteAll();
        logger.info("删除同步表组织[{}]条记录",deleteOrgCount);
        int deletePersonCount = msPersonService.deleteAll();
        logger.info("删除同步表人员[{}]条记录",deletePersonCount);
        int deleteUserCount = msUserService.deleteAll();
        logger.info("删除同步表用户[{}]条记录",deleteUserCount);
        //***************************************** 删除同步表的信息 结束 *****************************************

        //保存所有地市插入的ORGID
        List<String> cityORGID = new ArrayList<String>();

        //******************************************* 同步南京信息 开始 *******************************************
        MsDeptEntity messageNJDeptEntity = null;
        for (NJDeptEntity njDept : njDeptInfo){
            messageNJDeptEntity = new MsDeptEntity();
            messageNJDeptEntity.setDeptId(njDept.getDeptId());
            messageNJDeptEntity.setParentDeptId(njDept.getParentDeptId());
            messageNJDeptEntity.setDeptCode(njDept.getDeptCode());
            messageNJDeptEntity.setDeptName(njDept.getDeptName());
            messageNJDeptEntity.setDeptSimpname(njDept.getSimpName());
            messageNJDeptEntity.setOrgId(njDept.getBelongOrgId());
            messageNJDeptEntity.setCreateDate(njDept.getCreateDate());
            messageNJDeptEntity.setOrd(njDept.getOrd());
            messageNJDeptEntity.setState(njDept.getState());
            logger.info("Dept,南京插入同步表,deptId为[{}]",messageNJDeptEntity.getDeptId());
            msDeptService.saveEntity(messageNJDeptEntity);
        }

        MsOrgEntity messageNJOrgEntity = null;
        for (NJOrgEntity njOrg : njOrgInfo){
            messageNJOrgEntity = new MsOrgEntity();
            messageNJOrgEntity.setOrgId(njOrg.getOrgId());
            messageNJOrgEntity.setParentOrgId(njOrg.getParentOrgId());
            messageNJOrgEntity.setOrgCode(njOrg.getOrgCode());
            messageNJOrgEntity.setOrgName(njOrg.getOrgName());
            messageNJOrgEntity.setOrgSimpname(njOrg.getSimpName());
            messageNJOrgEntity.setOrgDblink(njOrg.getDbLinkName());
            messageNJOrgEntity.setOrgLevel(njOrg.getLevelName() != null ? Integer.valueOf(njOrg.getLevelName()) : null);
            messageNJOrgEntity.setCreateDate(njOrg.getCreateDate());
            messageNJOrgEntity.setOrd(njOrg.getOrd());
            messageNJOrgEntity.setState(njOrg.getState());
            logger.info("Org,南京插入同步表,orgId为[{}]",messageNJOrgEntity.getOrgId());
            msOrgService.saveEntity(messageNJOrgEntity);
            if(!cityORGID.contains(messageNJOrgEntity.getOrgId())){
                cityORGID.add(messageNJOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageNJPersonEntity = null;
        for (NJPersonEntity njPerson : njPersonInfo){
            messageNJPersonEntity = new MsPersonEntity();
            messageNJPersonEntity.setPersonId(njPerson.getPersonId());
            messageNJPersonEntity.setPersonName(njPerson.getName());
            messageNJPersonEntity.setDeptId(njPerson.getDeptId());
            messageNJPersonEntity.setOrgId(njPerson.getOrgId());
            messageNJPersonEntity.setPersonDuty(njPerson.getDuty());
            messageNJPersonEntity.setPersonOfficial(njPerson.getRoomnumber());
            messageNJPersonEntity.setCreateDate(njPerson.getCreateDate());
            messageNJPersonEntity.setOrd(njPerson.getOrd());
            messageNJPersonEntity.setState(njPerson.getState());
            logger.info("Person,南京插入同步表,personId为[{}]",messageNJPersonEntity.getPersonId());
            msPersonService.saveEntity(messageNJPersonEntity);
        }

        MsUserEntity messageNJUserEntity = null;
        for (NJUserEntity njUser : njUserInfo){
            messageNJUserEntity = new MsUserEntity();
            messageNJUserEntity.setUserId(njUser.getUserId());
            messageNJUserEntity.setUsername(njUser.getUserStaffCode());
            messageNJUserEntity.setPassword(njUser.getUserPassword());
            messageNJUserEntity.setPersonId(njUser.getPersonId());
            messageNJUserEntity.setDeptId(njUser.getDeptId());
            messageNJUserEntity.setOrgId(njUser.getOrgId());
            messageNJUserEntity.setUserType(njUser.getUserType());
            messageNJUserEntity.setCreateDate(njUser.getCreateDate());
            messageNJUserEntity.setOrd(njUser.getOrd());
            messageNJUserEntity.setIsAllowLogin(njUser.getIfAllowLogin() != null ? njUser.getIfAllowLogin().toString() : null);
            messageNJUserEntity.setState(njUser.getState());
            logger.info("User,南京插入同步表,userId为[{}]",messageNJUserEntity.getUserId());
            msUserService.saveEntity(messageNJUserEntity);
        }
        //******************************************* 同步南京信息 结束 *******************************************

        //******************************************* 同步无锡信息 开始 *******************************************
        MsDeptEntity messageWXDeptEntity = null;
        for (WXDeptEntity wxDept : wxDeptInfo){
            messageWXDeptEntity = new MsDeptEntity();
            messageWXDeptEntity.setDeptId(wxDept.getDeptId());
            messageWXDeptEntity.setParentDeptId(wxDept.getParentDeptId());
            messageWXDeptEntity.setDeptCode(wxDept.getDeptCode());
            messageWXDeptEntity.setDeptName(wxDept.getDeptName());
            messageWXDeptEntity.setDeptSimpname(wxDept.getSimpName());
            messageWXDeptEntity.setOrgId(wxDept.getBelongOrgId());
            messageWXDeptEntity.setCreateDate(wxDept.getCreateDate());
            messageWXDeptEntity.setOrd(wxDept.getOrd());
            messageWXDeptEntity.setState(wxDept.getState());
            logger.info("Dept,无锡插入同步表,deptId为[{}]",messageWXDeptEntity.getDeptId());
            msDeptService.saveEntity(messageWXDeptEntity);
        }

        MsOrgEntity messageWXOrgEntity = null;
        for (WXOrgEntity wxOrg : wxOrgInfo){
            messageWXOrgEntity = new MsOrgEntity();
            messageWXOrgEntity.setOrgId(wxOrg.getOrgId());
            messageWXOrgEntity.setParentOrgId(wxOrg.getParentOrgId());
            messageWXOrgEntity.setOrgCode(wxOrg.getOrgCode());
            messageWXOrgEntity.setOrgName(wxOrg.getOrgName());
            messageWXOrgEntity.setOrgSimpname(wxOrg.getSimpName());
            messageWXOrgEntity.setOrgDblink(wxOrg.getDbLinkName());
            messageWXOrgEntity.setOrgLevel(wxOrg.getLevelName() != null ? Integer.valueOf(wxOrg.getLevelName()) : null);
            messageWXOrgEntity.setCreateDate(wxOrg.getCreateDate());
            messageWXOrgEntity.setOrd(wxOrg.getOrd());
            messageWXOrgEntity.setState(wxOrg.getState());
            logger.info("Org,无锡插入同步表,orgId为[{}]",messageWXOrgEntity.getOrgId());
            msOrgService.saveEntity(messageWXOrgEntity);
            if(!cityORGID.contains(messageWXOrgEntity.getOrgId())){
                cityORGID.add(messageWXOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageWXPersonEntity = null;
        for (WXPersonEntity wxPerson : wxPersonInfo){
            messageWXPersonEntity = new MsPersonEntity();
            messageWXPersonEntity.setPersonId(wxPerson.getPersonId());
            messageWXPersonEntity.setPersonName(wxPerson.getName());
            messageWXPersonEntity.setDeptId(wxPerson.getDeptId());
            messageWXPersonEntity.setOrgId(wxPerson.getOrgId());
            messageWXPersonEntity.setPersonDuty(wxPerson.getDuty());
            messageWXPersonEntity.setPersonOfficial(wxPerson.getRoomnumber());
            messageWXPersonEntity.setCreateDate(wxPerson.getCreateDate());
            messageWXPersonEntity.setOrd(wxPerson.getOrd());
            messageWXPersonEntity.setState(wxPerson.getState());
            logger.info("Person,无锡插入同步表,personId为[{}]",messageWXPersonEntity.getPersonId());
            msPersonService.saveEntity(messageWXPersonEntity);
        }

        MsUserEntity messageWXUserEntity = null;
        for (WXUserEntity wxUser : wxUserInfo){
            messageWXUserEntity = new MsUserEntity();
            messageWXUserEntity.setUserId(wxUser.getUserId());
            messageWXUserEntity.setUsername(wxUser.getUserStaffCode());
            messageWXUserEntity.setPassword(wxUser.getUserPassword());
            messageWXUserEntity.setPersonId(wxUser.getPersonId());
            messageWXUserEntity.setDeptId(wxUser.getDeptId());
            messageWXUserEntity.setOrgId(wxUser.getOrgId());
            messageWXUserEntity.setUserType(wxUser.getUserType());
            messageWXUserEntity.setCreateDate(wxUser.getCreateDate());
            messageWXUserEntity.setOrd(wxUser.getOrd());
            messageWXUserEntity.setIsAllowLogin(wxUser.getIfAllowLogin() != null ? wxUser.getIfAllowLogin().toString() : null);
            messageWXUserEntity.setState(wxUser.getState());
            logger.info("User,无锡插入同步表,userId为[{}]",messageWXUserEntity.getUserId());
            msUserService.saveEntity(messageWXUserEntity);
        }
        //******************************************* 同步无锡信息 结束 *******************************************

        //******************************************* 同步徐州信息 开始 *******************************************
        MsDeptEntity messageXZDeptEntity = null;
        for (XZDeptEntity xzDept : xzDeptInfo){
            messageXZDeptEntity = new MsDeptEntity();
            messageXZDeptEntity.setDeptId(xzDept.getDeptId());
            messageXZDeptEntity.setParentDeptId(xzDept.getParentDeptId());
            messageXZDeptEntity.setDeptCode(xzDept.getDeptCode());
            messageXZDeptEntity.setDeptName(xzDept.getDeptName());
            messageXZDeptEntity.setDeptSimpname(xzDept.getSimpName());
            messageXZDeptEntity.setOrgId(xzDept.getBelongOrgId());
            messageXZDeptEntity.setCreateDate(xzDept.getCreateDate());
            messageXZDeptEntity.setOrd(xzDept.getOrd());
            messageXZDeptEntity.setState(xzDept.getState());
            logger.info("Dept,徐州插入同步表,deptId为[{}]",messageXZDeptEntity.getDeptId());
            msDeptService.saveEntity(messageXZDeptEntity);
        }

        MsOrgEntity messageXZOrgEntity = null;
        for (XZOrgEntity xzOrg : xzOrgInfo){
            messageXZOrgEntity = new MsOrgEntity();
            messageXZOrgEntity.setOrgId(xzOrg.getOrgId());
            messageXZOrgEntity.setParentOrgId(xzOrg.getParentOrgId());
            messageXZOrgEntity.setOrgCode(xzOrg.getOrgCode());
            messageXZOrgEntity.setOrgName(xzOrg.getOrgName());
            messageXZOrgEntity.setOrgSimpname(xzOrg.getSimpName());
            messageXZOrgEntity.setOrgDblink(xzOrg.getDbLinkName());
            messageXZOrgEntity.setOrgLevel(xzOrg.getLevelName() != null ? Integer.valueOf(xzOrg.getLevelName()) : null);
            messageXZOrgEntity.setCreateDate(xzOrg.getCreateDate());
            messageXZOrgEntity.setOrd(xzOrg.getOrd());
            messageXZOrgEntity.setState(xzOrg.getState());
            logger.info("Org,徐州插入同步表,orgId为[{}]",messageXZOrgEntity.getOrgId());
            msOrgService.saveEntity(messageXZOrgEntity);
            if(!cityORGID.contains(messageXZOrgEntity.getOrgId())){
                cityORGID.add(messageXZOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageXZPersonEntity = null;
        for (XZPersonEntity xzPerson : xzPersonInfo){
            messageXZPersonEntity = new MsPersonEntity();
            messageXZPersonEntity.setPersonId(xzPerson.getPersonId());
            messageXZPersonEntity.setPersonName(xzPerson.getName());
            messageXZPersonEntity.setDeptId(xzPerson.getDeptId());
            messageXZPersonEntity.setOrgId(xzPerson.getOrgId());
            messageXZPersonEntity.setPersonDuty(xzPerson.getDuty());
            messageXZPersonEntity.setPersonOfficial(xzPerson.getRoomnumber());
            messageXZPersonEntity.setCreateDate(xzPerson.getCreateDate());
            messageXZPersonEntity.setOrd(xzPerson.getOrd());
            messageXZPersonEntity.setState(xzPerson.getState());
            logger.info("Person,徐州插入同步表,personId为[{}]",messageXZPersonEntity.getPersonId());
            msPersonService.saveEntity(messageXZPersonEntity);
        }

        MsUserEntity messageXZUserEntity = null;
        for (XZUserEntity xzUser : xzUserInfo){
            messageXZUserEntity = new MsUserEntity();
            messageXZUserEntity.setUserId(xzUser.getUserId());
            messageXZUserEntity.setUsername(xzUser.getUserStaffCode());
            messageXZUserEntity.setPassword(xzUser.getUserPassword());
            messageXZUserEntity.setPersonId(xzUser.getPersonId());
            messageXZUserEntity.setDeptId(xzUser.getDeptId());
            messageXZUserEntity.setOrgId(xzUser.getOrgId());
            messageXZUserEntity.setUserType(xzUser.getUserType());
            messageXZUserEntity.setCreateDate(xzUser.getCreateDate());
            messageXZUserEntity.setOrd(xzUser.getOrd());
            messageXZUserEntity.setIsAllowLogin(xzUser.getIfAllowLogin() != null ? xzUser.getIfAllowLogin().toString() : null);
            messageXZUserEntity.setState(xzUser.getState());
            logger.info("User,徐州插入同步表,userId为[{}]",messageXZUserEntity.getUserId());
            msUserService.saveEntity(messageXZUserEntity);
        }
        //******************************************* 同步徐州信息 结束 *******************************************

        //******************************************* 同步常州信息 开始 *******************************************
        MsDeptEntity messageCZDeptEntity = null;
        for (CZDeptEntity czDept : czDeptInfo){
            messageCZDeptEntity = new MsDeptEntity();
            messageCZDeptEntity.setDeptId(czDept.getDeptId());
            messageCZDeptEntity.setParentDeptId(czDept.getParentDeptId());
            messageCZDeptEntity.setDeptCode(czDept.getDeptCode());
            messageCZDeptEntity.setDeptName(czDept.getDeptName());
            messageCZDeptEntity.setDeptSimpname(czDept.getSimpName());
            messageCZDeptEntity.setOrgId(czDept.getBelongOrgId());
            messageCZDeptEntity.setCreateDate(czDept.getCreateDate());
            messageCZDeptEntity.setOrd(czDept.getOrd());
            messageCZDeptEntity.setState(czDept.getState());
            logger.info("Dept,常州插入同步表,deptId为[{}]",messageCZDeptEntity.getDeptId());
            msDeptService.saveEntity(messageCZDeptEntity);
        }

        MsOrgEntity messageCZOrgEntity = null;
        for (CZOrgEntity czOrg : czOrgInfo){
            messageCZOrgEntity = new MsOrgEntity();
            messageCZOrgEntity.setOrgId(czOrg.getOrgId());
            messageCZOrgEntity.setParentOrgId(czOrg.getParentOrgId());
            messageCZOrgEntity.setOrgCode(czOrg.getOrgCode());
            messageCZOrgEntity.setOrgName(czOrg.getOrgName());
            messageCZOrgEntity.setOrgSimpname(czOrg.getSimpName());
            messageCZOrgEntity.setOrgDblink(czOrg.getDbLinkName());
            messageCZOrgEntity.setOrgLevel(czOrg.getLevelName() != null ? Integer.valueOf(czOrg.getLevelName()) : null);
            messageCZOrgEntity.setCreateDate(czOrg.getCreateDate());
            messageCZOrgEntity.setOrd(czOrg.getOrd());
            messageCZOrgEntity.setState(czOrg.getState());
            logger.info("Org,常州插入同步表,orgId为[{}]",messageCZOrgEntity.getOrgId());
            msOrgService.saveEntity(messageCZOrgEntity);
            if(!cityORGID.contains(messageCZOrgEntity.getOrgId())){
                cityORGID.add(messageCZOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageCZPersonEntity = null;
        for (CZPersonEntity czPerson : czPersonInfo){
            messageCZPersonEntity = new MsPersonEntity();
            messageCZPersonEntity.setPersonId(czPerson.getPersonId());
            messageCZPersonEntity.setPersonName(czPerson.getName());
            messageCZPersonEntity.setDeptId(czPerson.getDeptId());
            messageCZPersonEntity.setOrgId(czPerson.getOrgId());
            messageCZPersonEntity.setPersonDuty(czPerson.getDuty());
            messageCZPersonEntity.setPersonOfficial(czPerson.getRoomnumber());
            messageCZPersonEntity.setCreateDate(czPerson.getCreateDate());
            messageCZPersonEntity.setOrd(czPerson.getOrd());
            messageCZPersonEntity.setState(czPerson.getState());
            logger.info("Person,常州插入同步表,personId为[{}]",messageCZPersonEntity.getPersonId());
            msPersonService.saveEntity(messageCZPersonEntity);
        }

        MsUserEntity messageCZUserEntity = null;
        for (CZUserEntity czUser : czUserInfo){
            messageCZUserEntity = new MsUserEntity();
            messageCZUserEntity.setUserId(czUser.getUserId());
            messageCZUserEntity.setUsername(czUser.getUserStaffCode());
            messageCZUserEntity.setPassword(czUser.getUserPassword());
            messageCZUserEntity.setPersonId(czUser.getPersonId());
            messageCZUserEntity.setDeptId(czUser.getDeptId());
            messageCZUserEntity.setOrgId(czUser.getOrgId());
            messageCZUserEntity.setUserType(czUser.getUserType());
            messageCZUserEntity.setCreateDate(czUser.getCreateDate());
            messageCZUserEntity.setOrd(czUser.getOrd());
            messageCZUserEntity.setIsAllowLogin(czUser.getIfAllowLogin() != null ? czUser.getIfAllowLogin().toString() : null);
            messageCZUserEntity.setState(czUser.getState());
            logger.info("User,常州插入同步表,userId为[{}]",messageCZUserEntity.getUserId());
            msUserService.saveEntity(messageCZUserEntity);
        }
        //******************************************* 同步常州信息 结束 *******************************************

        //******************************************* 同步苏州信息 开始 *******************************************
        MsDeptEntity messageSZDeptEntity = null;
        for (SZDeptEntity szDept : szDeptInfo){
            messageSZDeptEntity = new MsDeptEntity();
            messageSZDeptEntity.setDeptId(szDept.getDeptId());
            messageSZDeptEntity.setParentDeptId(szDept.getParentDeptId());
            messageSZDeptEntity.setDeptCode(szDept.getDeptCode());
            messageSZDeptEntity.setDeptName(szDept.getDeptName());
            messageSZDeptEntity.setDeptSimpname(szDept.getSimpName());
            messageSZDeptEntity.setOrgId(szDept.getBelongOrgId());
            messageSZDeptEntity.setCreateDate(szDept.getCreateDate());
            messageSZDeptEntity.setOrd(szDept.getOrd());
            messageSZDeptEntity.setState(szDept.getState());
            logger.info("Dept,苏州插入同步表,deptId为[{}]",messageSZDeptEntity.getDeptId());
            msDeptService.saveEntity(messageSZDeptEntity);
        }

        MsOrgEntity messageSZOrgEntity = null;
        for (SZOrgEntity szOrg : szOrgInfo){
            messageSZOrgEntity = new MsOrgEntity();
            messageSZOrgEntity.setOrgId(szOrg.getOrgId());
            messageSZOrgEntity.setParentOrgId(szOrg.getParentOrgId());
            messageSZOrgEntity.setOrgCode(szOrg.getOrgCode());
            messageSZOrgEntity.setOrgName(szOrg.getOrgName());
            messageSZOrgEntity.setOrgSimpname(szOrg.getSimpName());
            messageSZOrgEntity.setOrgDblink(szOrg.getDbLinkName());
            messageSZOrgEntity.setOrgLevel(szOrg.getLevelName() != null ? Integer.valueOf(szOrg.getLevelName()) : null);
            messageSZOrgEntity.setCreateDate(szOrg.getCreateDate());
            messageSZOrgEntity.setOrd(szOrg.getOrd());
            messageSZOrgEntity.setState(szOrg.getState());
            logger.info("Org,苏州插入同步表,orgId为[{}]",messageSZOrgEntity.getOrgId());
            msOrgService.saveEntity(messageSZOrgEntity);
            if(!cityORGID.contains(messageSZOrgEntity.getOrgId())){
                cityORGID.add(messageSZOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageSZPersonEntity = null;
        for (SZPersonEntity szPerson : szPersonInfo){
            messageSZPersonEntity = new MsPersonEntity();
            messageSZPersonEntity.setPersonId(szPerson.getPersonId());
            messageSZPersonEntity.setPersonName(szPerson.getName());
            messageSZPersonEntity.setDeptId(szPerson.getDeptId());
            messageSZPersonEntity.setOrgId(szPerson.getOrgId());
            messageSZPersonEntity.setPersonDuty(szPerson.getDuty());
            messageSZPersonEntity.setPersonOfficial(szPerson.getRoomnumber());
            messageSZPersonEntity.setCreateDate(szPerson.getCreateDate());
            messageSZPersonEntity.setOrd(szPerson.getOrd());
            messageSZPersonEntity.setState(szPerson.getState());
            logger.info("Person,苏州插入同步表,personId为[{}]",messageSZPersonEntity.getPersonId());
            msPersonService.saveEntity(messageSZPersonEntity);
        }

        MsUserEntity messageSZUserEntity = null;
        for (SZUserEntity szUser : szUserInfo){
            messageSZUserEntity = new MsUserEntity();
            messageSZUserEntity.setUserId(szUser.getUserId());
            messageSZUserEntity.setUsername(szUser.getUserStaffCode());
            messageSZUserEntity.setPassword(szUser.getUserPassword());
            messageSZUserEntity.setPersonId(szUser.getPersonId());
            messageSZUserEntity.setDeptId(szUser.getDeptId());
            messageSZUserEntity.setOrgId(szUser.getOrgId());
            messageSZUserEntity.setUserType(szUser.getUserType());
            messageSZUserEntity.setCreateDate(szUser.getCreateDate());
            messageSZUserEntity.setOrd(szUser.getOrd());
            messageSZUserEntity.setIsAllowLogin(szUser.getIfAllowLogin() != null ? szUser.getIfAllowLogin().toString() : null);
            messageSZUserEntity.setState(szUser.getState());
            logger.info("User,苏州插入同步表,userId为[{}]",messageSZUserEntity.getUserId());
            msUserService.saveEntity(messageSZUserEntity);
        }
        //******************************************* 同步苏州信息 结束 *******************************************

        //******************************************* 同步南通信息 开始 *******************************************
        MsDeptEntity messageNTDeptEntity = null;
        for (NTDeptEntity ntDept : ntDeptInfo){
            messageNTDeptEntity = new MsDeptEntity();
            messageNTDeptEntity.setDeptId(ntDept.getDeptId());
            messageNTDeptEntity.setParentDeptId(ntDept.getParentDeptId());
            messageNTDeptEntity.setDeptCode(ntDept.getDeptCode());
            messageNTDeptEntity.setDeptName(ntDept.getDeptName());
            messageNTDeptEntity.setDeptSimpname(ntDept.getSimpName());
            messageNTDeptEntity.setOrgId(ntDept.getBelongOrgId());
            messageNTDeptEntity.setCreateDate(ntDept.getCreateDate());
            messageNTDeptEntity.setOrd(ntDept.getOrd());
            messageNTDeptEntity.setState(ntDept.getState());
            logger.info("Dept,南通插入同步表,deptId为[{}]",messageNTDeptEntity.getDeptId());
            msDeptService.saveEntity(messageNTDeptEntity);
        }

        MsOrgEntity messageNTOrgEntity = null;
        for (NTOrgEntity ntOrg : ntOrgInfo){
            messageNTOrgEntity = new MsOrgEntity();
            messageNTOrgEntity.setOrgId(ntOrg.getOrgId());
            messageNTOrgEntity.setParentOrgId(ntOrg.getParentOrgId());
            messageNTOrgEntity.setOrgCode(ntOrg.getOrgCode());
            messageNTOrgEntity.setOrgName(ntOrg.getOrgName());
            messageNTOrgEntity.setOrgSimpname(ntOrg.getSimpName());
            messageNTOrgEntity.setOrgDblink(ntOrg.getDbLinkName());
            messageNTOrgEntity.setOrgLevel(ntOrg.getLevelName() != null ? Integer.valueOf(ntOrg.getLevelName()) : null);
            messageNTOrgEntity.setCreateDate(ntOrg.getCreateDate());
            messageNTOrgEntity.setOrd(ntOrg.getOrd());
            messageNTOrgEntity.setState(ntOrg.getState());
            logger.info("Org,南通插入同步表,orgId为[{}]",messageNTOrgEntity.getOrgId());
            msOrgService.saveEntity(messageNTOrgEntity);
            if(!cityORGID.contains(messageNTOrgEntity.getOrgId())){
                cityORGID.add(messageNTOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageNTPersonEntity = null;
        for (NTPersonEntity ntPerson : ntPersonInfo){
            messageNTPersonEntity = new MsPersonEntity();
            messageNTPersonEntity.setPersonId(ntPerson.getPersonId());
            messageNTPersonEntity.setPersonName(ntPerson.getName());
            messageNTPersonEntity.setDeptId(ntPerson.getDeptId());
            messageNTPersonEntity.setOrgId(ntPerson.getOrgId());
            messageNTPersonEntity.setPersonDuty(ntPerson.getDuty());
            messageNTPersonEntity.setPersonOfficial(ntPerson.getRoomnumber());
            messageNTPersonEntity.setCreateDate(ntPerson.getCreateDate());
            messageNTPersonEntity.setOrd(ntPerson.getOrd());
            messageNTPersonEntity.setState(ntPerson.getState());
            logger.info("Person,南通插入同步表,personId为[{}]",messageNTPersonEntity.getPersonId());
            msPersonService.saveEntity(messageNTPersonEntity);
        }

        MsUserEntity messageNTUserEntity = null;
        for (NTUserEntity ntUser : ntUserInfo){
            messageNTUserEntity = new MsUserEntity();
            messageNTUserEntity.setUserId(ntUser.getUserId());
            messageNTUserEntity.setUsername(ntUser.getUserStaffCode());
            messageNTUserEntity.setPassword(ntUser.getUserPassword());
            messageNTUserEntity.setPersonId(ntUser.getPersonId());
            messageNTUserEntity.setDeptId(ntUser.getDeptId());
            messageNTUserEntity.setOrgId(ntUser.getOrgId());
            messageNTUserEntity.setUserType(ntUser.getUserType());
            messageNTUserEntity.setCreateDate(ntUser.getCreateDate());
            messageNTUserEntity.setOrd(ntUser.getOrd());
            messageNTUserEntity.setIsAllowLogin(ntUser.getIfAllowLogin() != null ? ntUser.getIfAllowLogin().toString() : null);
            messageNTUserEntity.setState(ntUser.getState());
            logger.info("User,南通插入同步表,userId为[{}]",messageNTUserEntity.getUserId());
            msUserService.saveEntity(messageNTUserEntity);
        }
        //******************************************* 同步南通信息 结束 *******************************************

        //******************************************* 同步连云港信息 开始 *******************************************
        MsDeptEntity messageLYGDeptEntity = null;
        for (LYGDeptEntity lygDept : lygDeptInfo){
            messageLYGDeptEntity = new MsDeptEntity();
            messageLYGDeptEntity.setDeptId(lygDept.getDeptId());
            messageLYGDeptEntity.setParentDeptId(lygDept.getParentDeptId());
            messageLYGDeptEntity.setDeptCode(lygDept.getDeptCode());
            messageLYGDeptEntity.setDeptName(lygDept.getDeptName());
            messageLYGDeptEntity.setDeptSimpname(lygDept.getSimpName());
            messageLYGDeptEntity.setOrgId(lygDept.getBelongOrgId());
            messageLYGDeptEntity.setCreateDate(lygDept.getCreateDate());
            messageLYGDeptEntity.setOrd(lygDept.getOrd());
            messageLYGDeptEntity.setState(lygDept.getState());
            logger.info("Dept,连云港插入同步表,deptId为[{}]",messageLYGDeptEntity.getDeptId());
            msDeptService.saveEntity(messageLYGDeptEntity);
        }

        MsOrgEntity messageLYGOrgEntity = null;
        for (LYGOrgEntity lygOrg : lygOrgInfo){
            messageLYGOrgEntity = new MsOrgEntity();
            messageLYGOrgEntity.setOrgId(lygOrg.getOrgId());
            messageLYGOrgEntity.setParentOrgId(lygOrg.getParentOrgId());
            messageLYGOrgEntity.setOrgCode(lygOrg.getOrgCode());
            messageLYGOrgEntity.setOrgName(lygOrg.getOrgName());
            messageLYGOrgEntity.setOrgSimpname(lygOrg.getSimpName());
            messageLYGOrgEntity.setOrgDblink(lygOrg.getDbLinkName());
            messageLYGOrgEntity.setOrgLevel(lygOrg.getLevelName() != null ? Integer.valueOf(lygOrg.getLevelName()) : null);
            messageLYGOrgEntity.setCreateDate(lygOrg.getCreateDate());
            messageLYGOrgEntity.setOrd(lygOrg.getOrd());
            messageLYGOrgEntity.setState(lygOrg.getState());
            logger.info("Org,连云港插入同步表,orgId为[{}]",messageLYGOrgEntity.getOrgId());
            msOrgService.saveEntity(messageLYGOrgEntity);
            if(!cityORGID.contains(messageLYGOrgEntity.getOrgId())){
                cityORGID.add(messageLYGOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageLYGPersonEntity = null;
        for (LYGPersonEntity lygPerson : lygPersonInfo){
            messageLYGPersonEntity = new MsPersonEntity();
            messageLYGPersonEntity.setPersonId(lygPerson.getPersonId());
            messageLYGPersonEntity.setPersonName(lygPerson.getName());
            messageLYGPersonEntity.setDeptId(lygPerson.getDeptId());
            messageLYGPersonEntity.setOrgId(lygPerson.getOrgId());
            messageLYGPersonEntity.setPersonDuty(lygPerson.getDuty());
            messageLYGPersonEntity.setPersonOfficial(lygPerson.getRoomnumber());
            messageLYGPersonEntity.setCreateDate(lygPerson.getCreateDate());
            messageLYGPersonEntity.setOrd(lygPerson.getOrd());
            messageLYGPersonEntity.setState(lygPerson.getState());
            logger.info("Person,连云港插入同步表,personId为[{}]",messageLYGPersonEntity.getPersonId());
            msPersonService.saveEntity(messageLYGPersonEntity);
        }

        MsUserEntity messageLYGUserEntity = null;
        for (LYGUserEntity lygUser : lygUserInfo){
            messageLYGUserEntity = new MsUserEntity();
            messageLYGUserEntity.setUserId(lygUser.getUserId());
            messageLYGUserEntity.setUsername(lygUser.getUserStaffCode());
            messageLYGUserEntity.setPassword(lygUser.getUserPassword());
            messageLYGUserEntity.setPersonId(lygUser.getPersonId());
            messageLYGUserEntity.setDeptId(lygUser.getDeptId());
            messageLYGUserEntity.setOrgId(lygUser.getOrgId());
            messageLYGUserEntity.setUserType(lygUser.getUserType());
            messageLYGUserEntity.setCreateDate(lygUser.getCreateDate());
            messageLYGUserEntity.setOrd(lygUser.getOrd());
            messageLYGUserEntity.setIsAllowLogin(lygUser.getIfAllowLogin() != null ? lygUser.getIfAllowLogin().toString() : null);
            messageLYGUserEntity.setState(lygUser.getState());
            logger.info("User,连云港插入同步表,userId为[{}]",messageLYGUserEntity.getUserId());
            msUserService.saveEntity(messageLYGUserEntity);
        }
        //******************************************* 同步连云港信息 结束 *******************************************

        //******************************************* 同步淮安信息 开始 *******************************************
        MsDeptEntity messageHADeptEntity = null;
        for (HADeptEntity haDept : haDeptInfo){
            messageHADeptEntity = new MsDeptEntity();
            messageHADeptEntity.setDeptId(haDept.getDeptId());
            messageHADeptEntity.setParentDeptId(haDept.getParentDeptId());
            messageHADeptEntity.setDeptCode(haDept.getDeptCode());
            messageHADeptEntity.setDeptName(haDept.getDeptName());
            messageHADeptEntity.setDeptSimpname(haDept.getSimpName());
            messageHADeptEntity.setOrgId(haDept.getBelongOrgId());
            messageHADeptEntity.setCreateDate(haDept.getCreateDate());
            messageHADeptEntity.setOrd(haDept.getOrd());
            messageHADeptEntity.setState(haDept.getState());
            logger.info("Dept,淮安插入同步表,deptId为[{}]",messageHADeptEntity.getDeptId());
            msDeptService.saveEntity(messageHADeptEntity);
        }

        MsOrgEntity messageHAOrgEntity = null;
        for (HAOrgEntity haOrg : haOrgInfo){
            messageHAOrgEntity = new MsOrgEntity();
            messageHAOrgEntity.setOrgId(haOrg.getOrgId());
            messageHAOrgEntity.setParentOrgId(haOrg.getParentOrgId());
            messageHAOrgEntity.setOrgCode(haOrg.getOrgCode());
            messageHAOrgEntity.setOrgName(haOrg.getOrgName());
            messageHAOrgEntity.setOrgSimpname(haOrg.getSimpName());
            messageHAOrgEntity.setOrgDblink(haOrg.getDbLinkName());
            messageHAOrgEntity.setOrgLevel(haOrg.getLevelName() != null ? Integer.valueOf(haOrg.getLevelName()) : null);
            messageHAOrgEntity.setCreateDate(haOrg.getCreateDate());
            messageHAOrgEntity.setOrd(haOrg.getOrd());
            messageHAOrgEntity.setState(haOrg.getState());
            logger.info("Org,淮安插入同步表,orgId为[{}]",messageHAOrgEntity.getOrgId());
            msOrgService.saveEntity(messageHAOrgEntity);
            if(!cityORGID.contains(messageHAOrgEntity.getOrgId())){
                cityORGID.add(messageHAOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageHAPersonEntity = null;
        for (HAPersonEntity haPerson : haPersonInfo){
            messageHAPersonEntity = new MsPersonEntity();
            messageHAPersonEntity.setPersonId(haPerson.getPersonId());
            messageHAPersonEntity.setPersonName(haPerson.getName());
            messageHAPersonEntity.setDeptId(haPerson.getDeptId());
            messageHAPersonEntity.setOrgId(haPerson.getOrgId());
            messageHAPersonEntity.setPersonDuty(haPerson.getDuty());
            messageHAPersonEntity.setPersonOfficial(haPerson.getRoomnumber());
            messageHAPersonEntity.setCreateDate(haPerson.getCreateDate());
            messageHAPersonEntity.setOrd(haPerson.getOrd());
            messageHAPersonEntity.setState(haPerson.getState());
            logger.info("Person,淮安插入同步表,personId为[{}]",messageHAPersonEntity.getPersonId());
            msPersonService.saveEntity(messageHAPersonEntity);
        }

        MsUserEntity messageHAUserEntity = null;
        for (HAUserEntity haUser : haUserInfo){
            messageHAUserEntity = new MsUserEntity();
            messageHAUserEntity.setUserId(haUser.getUserId());
            messageHAUserEntity.setUsername(haUser.getUserStaffCode());
            messageHAUserEntity.setPassword(haUser.getUserPassword());
            messageHAUserEntity.setPersonId(haUser.getPersonId());
            messageHAUserEntity.setDeptId(haUser.getDeptId());
            messageHAUserEntity.setOrgId(haUser.getOrgId());
            messageHAUserEntity.setUserType(haUser.getUserType());
            messageHAUserEntity.setCreateDate(haUser.getCreateDate());
            messageHAUserEntity.setOrd(haUser.getOrd());
            messageHAUserEntity.setIsAllowLogin(haUser.getIfAllowLogin() != null ? haUser.getIfAllowLogin().toString() : null);
            messageHAUserEntity.setState(haUser.getState());
            logger.info("User,淮安插入同步表,userId为[{}]",messageHAUserEntity.getUserId());
            msUserService.saveEntity(messageHAUserEntity);
        }
        //******************************************* 同步淮安信息 结束 *******************************************

        //******************************************* 同步盐城信息 开始 *******************************************
        MsDeptEntity messageYCDeptEntity = null;
        for (YCDeptEntity ycDept : ycDeptInfo){
            messageYCDeptEntity = new MsDeptEntity();
            messageYCDeptEntity.setDeptId(ycDept.getDeptId());
            messageYCDeptEntity.setParentDeptId(ycDept.getParentDeptId());
            messageYCDeptEntity.setDeptCode(ycDept.getDeptCode());
            messageYCDeptEntity.setDeptName(ycDept.getDeptName());
            messageYCDeptEntity.setDeptSimpname(ycDept.getSimpName());
            messageYCDeptEntity.setOrgId(ycDept.getBelongOrgId());
            messageYCDeptEntity.setCreateDate(ycDept.getCreateDate());
            messageYCDeptEntity.setOrd(ycDept.getOrd());
            messageYCDeptEntity.setState(ycDept.getState());
            logger.info("Dept,盐城插入同步表,deptId为[{}]",messageYCDeptEntity.getDeptId());
            msDeptService.saveEntity(messageYCDeptEntity);
        }

        MsOrgEntity messageYCOrgEntity = null;
        for (YCOrgEntity ycOrg : ycOrgInfo){
            messageYCOrgEntity = new MsOrgEntity();
            messageYCOrgEntity.setOrgId(ycOrg.getOrgId());
            messageYCOrgEntity.setParentOrgId(ycOrg.getParentOrgId());
            messageYCOrgEntity.setOrgCode(ycOrg.getOrgCode());
            messageYCOrgEntity.setOrgName(ycOrg.getOrgName());
            messageYCOrgEntity.setOrgSimpname(ycOrg.getSimpName());
            messageYCOrgEntity.setOrgDblink(ycOrg.getDbLinkName());
            messageYCOrgEntity.setOrgLevel(ycOrg.getLevelName() != null ? Integer.valueOf(ycOrg.getLevelName()) : null);
            messageYCOrgEntity.setCreateDate(ycOrg.getCreateDate());
            messageYCOrgEntity.setOrd(ycOrg.getOrd());
            messageYCOrgEntity.setState(ycOrg.getState());
            logger.info("Org,盐城插入同步表,orgId为[{}]",messageYCOrgEntity.getOrgId());
            msOrgService.saveEntity(messageYCOrgEntity);
            if(!cityORGID.contains(messageYCOrgEntity.getOrgId())){
                cityORGID.add(messageYCOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageYCPersonEntity = null;
        for (YCPersonEntity ycPerson : ycPersonInfo){
            messageYCPersonEntity = new MsPersonEntity();
            messageYCPersonEntity.setPersonId(ycPerson.getPersonId());
            messageYCPersonEntity.setPersonName(ycPerson.getName());
            messageYCPersonEntity.setDeptId(ycPerson.getDeptId());
            messageYCPersonEntity.setOrgId(ycPerson.getOrgId());
            messageYCPersonEntity.setPersonDuty(ycPerson.getDuty());
            messageYCPersonEntity.setPersonOfficial(ycPerson.getRoomnumber());
            messageYCPersonEntity.setCreateDate(ycPerson.getCreateDate());
            messageYCPersonEntity.setOrd(ycPerson.getOrd());
            messageYCPersonEntity.setState(ycPerson.getState());
            logger.info("Person,盐城插入同步表,personId为[{}]",messageYCPersonEntity.getPersonId());
            msPersonService.saveEntity(messageYCPersonEntity);
        }

        MsUserEntity messageYCUserEntity = null;
        for (YCUserEntity ycUser : ycUserInfo){
            messageYCUserEntity = new MsUserEntity();
            messageYCUserEntity.setUserId(ycUser.getUserId());
            messageYCUserEntity.setUsername(ycUser.getUserStaffCode());
            messageYCUserEntity.setPassword(ycUser.getUserPassword());
            messageYCUserEntity.setPersonId(ycUser.getPersonId());
            messageYCUserEntity.setDeptId(ycUser.getDeptId());
            messageYCUserEntity.setOrgId(ycUser.getOrgId());
            messageYCUserEntity.setUserType(ycUser.getUserType());
            messageYCUserEntity.setCreateDate(ycUser.getCreateDate());
            messageYCUserEntity.setOrd(ycUser.getOrd());
            messageYCUserEntity.setIsAllowLogin(ycUser.getIfAllowLogin() != null ? ycUser.getIfAllowLogin().toString() : null);
            messageYCUserEntity.setState(ycUser.getState());
            logger.info("User,盐城插入同步表,userId为[{}]",messageYCUserEntity.getUserId());
            msUserService.saveEntity(messageYCUserEntity);
        }
        //******************************************* 同步盐城信息 结束 *******************************************

        //******************************************* 同步扬州信息 开始 *******************************************
        MsDeptEntity messageYZDeptEntity = null;
        for (YZDeptEntity yzDept : yzDeptInfo){
            messageYZDeptEntity = new MsDeptEntity();
            messageYZDeptEntity.setDeptId(yzDept.getDeptId());
            messageYZDeptEntity.setParentDeptId(yzDept.getParentDeptId());
            messageYZDeptEntity.setDeptCode(yzDept.getDeptCode());
            messageYZDeptEntity.setDeptName(yzDept.getDeptName());
            messageYZDeptEntity.setDeptSimpname(yzDept.getSimpName());
            messageYZDeptEntity.setOrgId(yzDept.getBelongOrgId());
            messageYZDeptEntity.setCreateDate(yzDept.getCreateDate());
            messageYZDeptEntity.setOrd(yzDept.getOrd());
            messageYZDeptEntity.setState(yzDept.getState());
            logger.info("Dept,扬州插入同步表,deptId为[{}]",messageYZDeptEntity.getDeptId());
            msDeptService.saveEntity(messageYZDeptEntity);
        }

        MsOrgEntity messageYZOrgEntity = null;
        for (YZOrgEntity yzOrg : yzOrgInfo){
            messageYZOrgEntity = new MsOrgEntity();
            messageYZOrgEntity.setOrgId(yzOrg.getOrgId());
            messageYZOrgEntity.setParentOrgId(yzOrg.getParentOrgId());
            messageYZOrgEntity.setOrgCode(yzOrg.getOrgCode());
            messageYZOrgEntity.setOrgName(yzOrg.getOrgName());
            messageYZOrgEntity.setOrgSimpname(yzOrg.getSimpName());
            messageYZOrgEntity.setOrgDblink(yzOrg.getDbLinkName());
            messageYZOrgEntity.setOrgLevel(yzOrg.getLevelName() != null ? Integer.valueOf(yzOrg.getLevelName()) : null);
            messageYZOrgEntity.setCreateDate(yzOrg.getCreateDate());
            messageYZOrgEntity.setOrd(yzOrg.getOrd());
            messageYZOrgEntity.setState(yzOrg.getState());
            logger.info("Org,扬州插入同步表,orgId为[{}]",messageYZOrgEntity.getOrgId());
            msOrgService.saveEntity(messageYZOrgEntity);
            if(!cityORGID.contains(messageYZOrgEntity.getOrgId())){
                cityORGID.add(messageYZOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageYZPersonEntity = null;
        for (YZPersonEntity yzPerson : yzPersonInfo){
            messageYZPersonEntity = new MsPersonEntity();
            messageYZPersonEntity.setPersonId(yzPerson.getPersonId());
            messageYZPersonEntity.setPersonName(yzPerson.getName());
            messageYZPersonEntity.setDeptId(yzPerson.getDeptId());
            messageYZPersonEntity.setOrgId(yzPerson.getOrgId());
            messageYZPersonEntity.setPersonDuty(yzPerson.getDuty());
            messageYZPersonEntity.setPersonOfficial(yzPerson.getRoomnumber());
            messageYZPersonEntity.setCreateDate(yzPerson.getCreateDate());
            messageYZPersonEntity.setOrd(yzPerson.getOrd());
            messageYZPersonEntity.setState(yzPerson.getState());
            logger.info("Person,扬州插入同步表,personId为[{}]",messageYZPersonEntity.getPersonId());
            msPersonService.saveEntity(messageYZPersonEntity);
        }

        MsUserEntity messageYZUserEntity = null;
        for (YZUserEntity yzUser : yzUserInfo){
            messageYZUserEntity = new MsUserEntity();
            messageYZUserEntity.setUserId(yzUser.getUserId());
            messageYZUserEntity.setUsername(yzUser.getUserStaffCode());
            messageYZUserEntity.setPassword(yzUser.getUserPassword());
            messageYZUserEntity.setPersonId(yzUser.getPersonId());
            messageYZUserEntity.setDeptId(yzUser.getDeptId());
            messageYZUserEntity.setOrgId(yzUser.getOrgId());
            messageYZUserEntity.setUserType(yzUser.getUserType());
            messageYZUserEntity.setCreateDate(yzUser.getCreateDate());
            messageYZUserEntity.setOrd(yzUser.getOrd());
            messageYZUserEntity.setIsAllowLogin(yzUser.getIfAllowLogin() != null ? yzUser.getIfAllowLogin().toString() : null);
            messageYZUserEntity.setState(yzUser.getState());
            logger.info("User,扬州插入同步表,userId为[{}]",messageYZUserEntity.getUserId());
            msUserService.saveEntity(messageYZUserEntity);
        }
        //******************************************* 同步扬州信息 结束 *******************************************

        //******************************************* 同步镇江信息 开始 *******************************************
        MsDeptEntity messageZJDeptEntity = null;
        for (ZJDeptEntity zjDept : zjDeptInfo){
            messageZJDeptEntity = new MsDeptEntity();
            messageZJDeptEntity.setDeptId(zjDept.getDeptId());
            messageZJDeptEntity.setParentDeptId(zjDept.getParentDeptId());
            messageZJDeptEntity.setDeptCode(zjDept.getDeptCode());
            messageZJDeptEntity.setDeptName(zjDept.getDeptName());
            messageZJDeptEntity.setDeptSimpname(zjDept.getSimpName());
            messageZJDeptEntity.setOrgId(zjDept.getBelongOrgId());
            messageZJDeptEntity.setCreateDate(zjDept.getCreateDate());
            messageZJDeptEntity.setOrd(zjDept.getOrd());
            messageZJDeptEntity.setState(zjDept.getState());
            logger.info("Dept,镇江插入同步表,deptId为[{}]",messageZJDeptEntity.getDeptId());
            msDeptService.saveEntity(messageZJDeptEntity);
        }

        MsOrgEntity messageZJOrgEntity = null;
        for (ZJOrgEntity zjOrg : zjOrgInfo){
            messageZJOrgEntity = new MsOrgEntity();
            messageZJOrgEntity.setOrgId(zjOrg.getOrgId());
            messageZJOrgEntity.setParentOrgId(zjOrg.getParentOrgId());
            messageZJOrgEntity.setOrgCode(zjOrg.getOrgCode());
            messageZJOrgEntity.setOrgName(zjOrg.getOrgName());
            messageZJOrgEntity.setOrgSimpname(zjOrg.getSimpName());
            messageZJOrgEntity.setOrgDblink(zjOrg.getDbLinkName());
            messageZJOrgEntity.setOrgLevel(zjOrg.getLevelName() != null ? Integer.valueOf(zjOrg.getLevelName()) : null);
            messageZJOrgEntity.setCreateDate(zjOrg.getCreateDate());
            messageZJOrgEntity.setOrd(zjOrg.getOrd());
            messageZJOrgEntity.setState(zjOrg.getState());
            logger.info("Org,镇江插入同步表,orgId为[{}]",messageZJOrgEntity.getOrgId());
            msOrgService.saveEntity(messageZJOrgEntity);
            if(!cityORGID.contains(messageZJOrgEntity.getOrgId())){
                cityORGID.add(messageZJOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageZJPersonEntity = null;
        for (ZJPersonEntity zjPerson : zjPersonInfo){
            messageZJPersonEntity = new MsPersonEntity();
            messageZJPersonEntity.setPersonId(zjPerson.getPersonId());
            messageZJPersonEntity.setPersonName(zjPerson.getName());
            messageZJPersonEntity.setDeptId(zjPerson.getDeptId());
            messageZJPersonEntity.setOrgId(zjPerson.getOrgId());
            messageZJPersonEntity.setPersonDuty(zjPerson.getDuty());
            messageZJPersonEntity.setPersonOfficial(zjPerson.getRoomnumber());
            messageZJPersonEntity.setCreateDate(zjPerson.getCreateDate());
            messageZJPersonEntity.setOrd(zjPerson.getOrd());
            messageZJPersonEntity.setState(zjPerson.getState());
            logger.info("Person,镇江插入同步表,personId为[{}]",messageZJPersonEntity.getPersonId());
            msPersonService.saveEntity(messageZJPersonEntity);
        }

        MsUserEntity messageZJUserEntity = null;
        for (ZJUserEntity zjUser : zjUserInfo){
            messageZJUserEntity = new MsUserEntity();
            messageZJUserEntity.setUserId(zjUser.getUserId());
            messageZJUserEntity.setUsername(zjUser.getUserStaffCode());
            messageZJUserEntity.setPassword(zjUser.getUserPassword());
            messageZJUserEntity.setPersonId(zjUser.getPersonId());
            messageZJUserEntity.setDeptId(zjUser.getDeptId());
            messageZJUserEntity.setOrgId(zjUser.getOrgId());
            messageZJUserEntity.setUserType(zjUser.getUserType());
            messageZJUserEntity.setCreateDate(zjUser.getCreateDate());
            messageZJUserEntity.setOrd(zjUser.getOrd());
            messageZJUserEntity.setIsAllowLogin(zjUser.getIfAllowLogin() != null ? zjUser.getIfAllowLogin().toString() : null);
            messageZJUserEntity.setState(zjUser.getState());
            logger.info("User,镇江插入同步表,userId为[{}]",messageZJUserEntity.getUserId());
            msUserService.saveEntity(messageZJUserEntity);
        }
        //******************************************* 同步镇江信息 结束 *******************************************

        //******************************************* 同步泰州信息 开始 *******************************************
        MsDeptEntity messageTZDeptEntity = null;
        for (TZDeptEntity tzDept : tzDeptInfo){
            messageTZDeptEntity = new MsDeptEntity();
            messageTZDeptEntity.setDeptId(tzDept.getDeptId());
            messageTZDeptEntity.setParentDeptId(tzDept.getParentDeptId());
            messageTZDeptEntity.setDeptCode(tzDept.getDeptCode());
            messageTZDeptEntity.setDeptName(tzDept.getDeptName());
            messageTZDeptEntity.setDeptSimpname(tzDept.getSimpName());
            messageTZDeptEntity.setOrgId(tzDept.getBelongOrgId());
            messageTZDeptEntity.setCreateDate(tzDept.getCreateDate());
            messageTZDeptEntity.setOrd(tzDept.getOrd());
            messageTZDeptEntity.setState(tzDept.getState());
            logger.info("Dept,泰州插入同步表,deptId为[{}]",messageTZDeptEntity.getDeptId());
            msDeptService.saveEntity(messageTZDeptEntity);
        }

        MsOrgEntity messageTZOrgEntity = null;
        for (TZOrgEntity tzOrg : tzOrgInfo){
            messageTZOrgEntity = new MsOrgEntity();
            messageTZOrgEntity.setOrgId(tzOrg.getOrgId());
            messageTZOrgEntity.setParentOrgId(tzOrg.getParentOrgId());
            messageTZOrgEntity.setOrgCode(tzOrg.getOrgCode());
            messageTZOrgEntity.setOrgName(tzOrg.getOrgName());
            messageTZOrgEntity.setOrgSimpname(tzOrg.getSimpName());
            messageTZOrgEntity.setOrgDblink(tzOrg.getDbLinkName());
            messageTZOrgEntity.setOrgLevel(tzOrg.getLevelName() != null ? Integer.valueOf(tzOrg.getLevelName()) : null);
            messageTZOrgEntity.setCreateDate(tzOrg.getCreateDate());
            messageTZOrgEntity.setOrd(tzOrg.getOrd());
            messageTZOrgEntity.setState(tzOrg.getState());
            logger.info("Org,泰州插入同步表,orgId为[{}]",messageTZOrgEntity.getOrgId());
            msOrgService.saveEntity(messageTZOrgEntity);
            if(!cityORGID.contains(messageTZOrgEntity.getOrgId())){
                cityORGID.add(messageTZOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageTZPersonEntity = null;
        for (TZPersonEntity tzPerson : tzPersonInfo){
            messageTZPersonEntity = new MsPersonEntity();
            messageTZPersonEntity.setPersonId(tzPerson.getPersonId());
            messageTZPersonEntity.setPersonName(tzPerson.getName());
            messageTZPersonEntity.setDeptId(tzPerson.getDeptId());
            messageTZPersonEntity.setOrgId(tzPerson.getOrgId());
            messageTZPersonEntity.setPersonDuty(tzPerson.getDuty());
            messageTZPersonEntity.setPersonOfficial(tzPerson.getRoomnumber());
            messageTZPersonEntity.setCreateDate(tzPerson.getCreateDate());
            messageTZPersonEntity.setOrd(tzPerson.getOrd());
            messageTZPersonEntity.setState(tzPerson.getState());
            logger.info("Person,泰州插入同步表,personId为[{}]",messageTZPersonEntity.getPersonId());
            msPersonService.saveEntity(messageTZPersonEntity);
        }

        MsUserEntity messageTZUserEntity = null;
        for (TZUserEntity tzUser : tzUserInfo){
            messageTZUserEntity = new MsUserEntity();
            messageTZUserEntity.setUserId(tzUser.getUserId());
            messageTZUserEntity.setUsername(tzUser.getUserStaffCode());
            messageTZUserEntity.setPassword(tzUser.getUserPassword());
            messageTZUserEntity.setPersonId(tzUser.getPersonId());
            messageTZUserEntity.setDeptId(tzUser.getDeptId());
            messageTZUserEntity.setOrgId(tzUser.getOrgId());
            messageTZUserEntity.setUserType(tzUser.getUserType());
            messageTZUserEntity.setCreateDate(tzUser.getCreateDate());
            messageTZUserEntity.setOrd(tzUser.getOrd());
            messageTZUserEntity.setIsAllowLogin(tzUser.getIfAllowLogin() != null ? tzUser.getIfAllowLogin().toString() : null);
            messageTZUserEntity.setState(tzUser.getState());
            logger.info("User,泰州插入同步表,userId为[{}]",messageTZUserEntity.getUserId());
            msUserService.saveEntity(messageTZUserEntity);
        }
        //******************************************* 同步泰州信息 结束 *******************************************

        //******************************************* 同步宿迁信息 开始 *******************************************
        MsDeptEntity messageSQDeptEntity = null;
        for (SQDeptEntity sqDept : sqDeptInfo){
            messageSQDeptEntity = new MsDeptEntity();
            messageSQDeptEntity.setDeptId(sqDept.getDeptId());
            messageSQDeptEntity.setParentDeptId(sqDept.getParentDeptId());
            messageSQDeptEntity.setDeptCode(sqDept.getDeptCode());
            messageSQDeptEntity.setDeptName(sqDept.getDeptName());
            messageSQDeptEntity.setDeptSimpname(sqDept.getSimpName());
            messageSQDeptEntity.setOrgId(sqDept.getBelongOrgId());
            messageSQDeptEntity.setCreateDate(sqDept.getCreateDate());
            messageSQDeptEntity.setOrd(sqDept.getOrd());
            messageSQDeptEntity.setState(sqDept.getState());
            logger.info("Dept,宿迁插入同步表,deptId为[{}]",messageSQDeptEntity.getDeptId());
            msDeptService.saveEntity(messageSQDeptEntity);
        }

        MsOrgEntity messageSQOrgEntity = null;
        for (SQOrgEntity sqOrg : sqOrgInfo){
            messageSQOrgEntity = new MsOrgEntity();
            messageSQOrgEntity.setOrgId(sqOrg.getOrgId());
            messageSQOrgEntity.setParentOrgId(sqOrg.getParentOrgId());
            messageSQOrgEntity.setOrgCode(sqOrg.getOrgCode());
            messageSQOrgEntity.setOrgName(sqOrg.getOrgName());
            messageSQOrgEntity.setOrgSimpname(sqOrg.getSimpName());
            messageSQOrgEntity.setOrgDblink(sqOrg.getDbLinkName());
            messageSQOrgEntity.setOrgLevel(sqOrg.getLevelName() != null ? Integer.valueOf(sqOrg.getLevelName()) : null);
            messageSQOrgEntity.setCreateDate(sqOrg.getCreateDate());
            messageSQOrgEntity.setOrd(sqOrg.getOrd());
            messageSQOrgEntity.setState(sqOrg.getState());
            logger.info("Org,宿迁插入同步表,orgId为[{}]",messageSQOrgEntity.getOrgId());
            msOrgService.saveEntity(messageSQOrgEntity);
            if(!cityORGID.contains(messageSQOrgEntity.getOrgId())){
                cityORGID.add(messageSQOrgEntity.getOrgId());
            }
        }

        MsPersonEntity messageSQPersonEntity = null;
        for (SQPersonEntity sqPerson : sqPersonInfo){
            messageSQPersonEntity = new MsPersonEntity();
            messageSQPersonEntity.setPersonId(sqPerson.getPersonId());
            messageSQPersonEntity.setPersonName(sqPerson.getName());
            messageSQPersonEntity.setDeptId(sqPerson.getDeptId());
            messageSQPersonEntity.setOrgId(sqPerson.getOrgId());
            messageSQPersonEntity.setPersonDuty(sqPerson.getDuty());
            messageSQPersonEntity.setPersonOfficial(sqPerson.getRoomnumber());
            messageSQPersonEntity.setCreateDate(sqPerson.getCreateDate());
            messageSQPersonEntity.setOrd(sqPerson.getOrd());
            messageSQPersonEntity.setState(sqPerson.getState());
            logger.info("Person,宿迁插入同步表,personId为[{}]",messageSQPersonEntity.getPersonId());
            msPersonService.saveEntity(messageSQPersonEntity);
        }

        MsUserEntity messageSQUserEntity = null;
        for (SQUserEntity sqUser : sqUserInfo){
            messageSQUserEntity = new MsUserEntity();
            messageSQUserEntity.setUserId(sqUser.getUserId());
            messageSQUserEntity.setUsername(sqUser.getUserStaffCode());
            messageSQUserEntity.setPassword(sqUser.getUserPassword());
            messageSQUserEntity.setPersonId(sqUser.getPersonId());
            messageSQUserEntity.setDeptId(sqUser.getDeptId());
            messageSQUserEntity.setOrgId(sqUser.getOrgId());
            messageSQUserEntity.setUserType(sqUser.getUserType());
            messageSQUserEntity.setCreateDate(sqUser.getCreateDate());
            messageSQUserEntity.setOrd(sqUser.getOrd());
            messageSQUserEntity.setIsAllowLogin(sqUser.getIfAllowLogin() != null ? sqUser.getIfAllowLogin().toString() : null);
            messageSQUserEntity.setState(sqUser.getState());
            logger.info("User,宿迁插入同步表,userId为[{}]",messageSQUserEntity.getUserId());
            msUserService.saveEntity(messageSQUserEntity);
        }
        //******************************************* 同步宿迁信息 结束 *******************************************

        //******************************************* 同步省局信息 开始 *******************************************

        //过滤掉地市中与省局相同的组织
        List<SJOrgEntity> sjOrgInfo = sjOrgService.queryAllList(cityORGID);


        MsDeptEntity messageSJDeptEntity = null;
        for (SJDeptEntity sjDept : sjDeptInfo){
            messageSJDeptEntity = new MsDeptEntity();
            messageSJDeptEntity.setDeptId(sjDept.getDeptId());
            messageSJDeptEntity.setParentDeptId(sjDept.getParentDeptId());
            messageSJDeptEntity.setDeptCode(sjDept.getDeptCode());
            messageSJDeptEntity.setDeptName(sjDept.getDeptName());
            messageSJDeptEntity.setDeptSimpname(sjDept.getSimpName());
            messageSJDeptEntity.setOrgId(sjDept.getBelongOrgId());
            messageSJDeptEntity.setCreateDate(sjDept.getCreateDate());
            messageSJDeptEntity.setOrd(sjDept.getOrd());
            messageSJDeptEntity.setState(sjDept.getState());
            logger.info("Dept,省局插入同步表,deptId为[{}]",messageSJDeptEntity.getDeptId());
            msDeptService.saveEntity(messageSJDeptEntity);
        }

        MsOrgEntity messageSJOrgEntity = null;
        for (SJOrgEntity sjOrg : sjOrgInfo){
            messageSJOrgEntity = new MsOrgEntity();
            messageSJOrgEntity.setOrgId(sjOrg.getOrgId());
            messageSJOrgEntity.setParentOrgId(sjOrg.getParentOrgId());
            messageSJOrgEntity.setOrgCode(sjOrg.getOrgCode());
            messageSJOrgEntity.setOrgName(sjOrg.getOrgName());
            messageSJOrgEntity.setOrgSimpname(sjOrg.getSimpName());
            messageSJOrgEntity.setOrgDblink(sjOrg.getDbLinkName());
            messageSJOrgEntity.setOrgLevel(sjOrg.getLevelName() != null ? Integer.valueOf(sjOrg.getLevelName()) : null);
            messageSJOrgEntity.setCreateDate(sjOrg.getCreateDate());
            messageSJOrgEntity.setOrd(sjOrg.getOrd());
            messageSJOrgEntity.setState(sjOrg.getState());
            logger.info("Org,省局插入同步表,orgId为[{}]",messageSJOrgEntity.getOrgId());
            msOrgService.saveEntity(messageSJOrgEntity);
        }

        MsPersonEntity messageSJPersonEntity = null;
        for (SJPersonEntity sjPerson : sjPersonInfo){
            messageSJPersonEntity = new MsPersonEntity();
            messageSJPersonEntity.setPersonId(sjPerson.getPersonId());
            messageSJPersonEntity.setPersonName(sjPerson.getName());
            messageSJPersonEntity.setDeptId(sjPerson.getDeptId());
            messageSJPersonEntity.setOrgId(sjPerson.getOrgId());
            messageSJPersonEntity.setPersonDuty(sjPerson.getDuty());
            messageSJPersonEntity.setPersonOfficial(sjPerson.getRoomnumber());
            messageSJPersonEntity.setCreateDate(sjPerson.getCreateDate());
            messageSJPersonEntity.setOrd(sjPerson.getOrd());
            messageSJPersonEntity.setState(sjPerson.getState());
            logger.info("Person,省局插入同步表,personId为[{}]",messageSJPersonEntity.getPersonId());
            msPersonService.saveEntity(messageSJPersonEntity);
        }

        MsUserEntity messageSJUserEntity = null;
        for (SJUserEntity sjUser : sjUserInfo){
            messageSJUserEntity = new MsUserEntity();
            messageSJUserEntity.setUserId(sjUser.getUserId());
            messageSJUserEntity.setUsername(sjUser.getUserStaffCode());
            messageSJUserEntity.setPassword(sjUser.getUserPassword());
            messageSJUserEntity.setPersonId(sjUser.getPersonId());
            messageSJUserEntity.setDeptId(sjUser.getDeptId());
            messageSJUserEntity.setOrgId(sjUser.getOrgId());
            messageSJUserEntity.setUserType(sjUser.getUserType());
            messageSJUserEntity.setCreateDate(sjUser.getCreateDate());
            messageSJUserEntity.setOrd(sjUser.getOrd());
            messageSJUserEntity.setIsAllowLogin(sjUser.getIfAllowLogin() != null ? sjUser.getIfAllowLogin().toString() : null);
            messageSJUserEntity.setState(sjUser.getState());
            logger.info("User,省局插入同步表,userId为[{}]",messageSJUserEntity.getUserId());
            msUserService.saveEntity(messageSJUserEntity);
        }
        //******************************************* 同步省局信息 结束 *******************************************
        //--------------------------------------------- 同一个事务中 结束---------------------------------------------
        logger.info("同步表同步数据成功！[{}]",getNowTime());
    }

    public void kTLToMain() throws Exception{
        //***************************************** 主表查询到的信息 开始 *****************************************
        List<MsMainDeptEntity> msMainDeptEntityList = msMainDeptService.queryAllList();
        List<MsMainOrgEntity> msMainOrgEntityList = msMainOrgService.queryAllList();
        List<MsMainPersonEntity> msMainPersonEntityList = msMainPersonService.queryAllList();
        List<MsMainUserEntity> msMainUserEntityList = msMainUserService.queryAllList();

        List<MsMainDeptEntity> msMainDeptEntityListTemp = new ArrayList<MsMainDeptEntity>();
        msMainDeptEntityListTemp.addAll(msMainDeptEntityList);
        List<MsMainOrgEntity> msMainOrgEntityListTemp = new ArrayList<MsMainOrgEntity>();
        msMainOrgEntityListTemp.addAll(msMainOrgEntityList);
        List<MsMainPersonEntity> msMainPersonEntityListTemp = new ArrayList<MsMainPersonEntity>();
        msMainPersonEntityListTemp.addAll(msMainPersonEntityList);
        List<MsMainUserEntity> msMainUserEntityListTemp = new ArrayList<MsMainUserEntity>();
        msMainUserEntityListTemp.addAll(msMainUserEntityList);
        //***************************************** 主表查询到的信息 结束 *****************************************

        //***************************************** 同步表查到的信息 开始 *****************************************
        List<MsDeptEntity> msDeptEntityList = msDeptService.queryAllList();
        List<MsOrgEntity> msOrgEntityList = msOrgService.queryAllList();
        List<MsPersonEntity> msPersonEntityList = msPersonService.queryAllList();
        List<MsUserEntity> msUserEntityList = msUserService.queryAllList();

        List<MsDeptEntity> msDeptEntityListTemp = new ArrayList<MsDeptEntity>();
        msDeptEntityListTemp.addAll(msDeptEntityList);
        List<MsOrgEntity> msOrgEntityListTemp = new ArrayList<MsOrgEntity>();
        msOrgEntityListTemp.addAll(msOrgEntityList);
        List<MsPersonEntity> msPersonEntityListTemp = new ArrayList<MsPersonEntity>();
        msPersonEntityListTemp.addAll(msPersonEntityList);
        List<MsUserEntity> msUserEntityListTemp = new ArrayList<MsUserEntity>();
        msUserEntityListTemp.addAll(msUserEntityList);
        //***************************************** 同步表查到的信息 结束 *****************************************

        //**************************************** 主表和同步表作比较 开始 ****************************************

        //******************* 部门 开始 *******************
        for(MsDeptEntity msDeptEntity : msDeptEntityList){
            for(MsMainDeptEntity msMainDeptEntity : msMainDeptEntityList){
                if(msDeptEntity.getDeptId().equals(msMainDeptEntity.getDeptId())){
                    //修改
                    msMainDeptEntity.setParentDeptId(msDeptEntity.getParentDeptId());
                    msMainDeptEntity.setDeptCode(msDeptEntity.getDeptCode());
                    msMainDeptEntity.setDeptName(msDeptEntity.getDeptName());
                    msMainDeptEntity.setDeptSimpname(msDeptEntity.getDeptSimpname());
                    msMainDeptEntity.setOrgId(msDeptEntity.getOrgId());
                    msMainDeptEntity.setCreateDate(msDeptEntity.getCreateDate());
                    msMainDeptEntity.setOrd(msDeptEntity.getOrd());
                    msMainDeptEntity.setState(msDeptEntity.getState());
                    logger.info("修改主表部门[{}]的记录",msMainDeptEntity.getDeptId());
                    msMainDeptService.updateByEntity(msMainDeptEntity);
                    msDeptEntityListTemp.remove(msDeptEntity);
                    msMainDeptEntityListTemp.remove(msMainDeptEntity);
                }
            }
        }

        for(MsMainDeptEntity msMainDeptEntity : msMainDeptEntityListTemp){
            //逻辑删除
            msMainDeptEntity.setState(1);
            logger.info("删除主表部门[{}]的记录",msMainDeptEntity.getDeptId());
            msMainDeptService.updateByEntity(msMainDeptEntity);
        }

        MsMainDeptEntity msMainDeptEntity = null;
        for(MsDeptEntity msDeptEntity : msDeptEntityListTemp){
            //插入
            msMainDeptEntity = new MsMainDeptEntity();
            msMainDeptEntity.setDeptId(msDeptEntity.getDeptId());
            msMainDeptEntity.setParentDeptId(msDeptEntity.getParentDeptId());
            msMainDeptEntity.setDeptCode(msDeptEntity.getDeptCode());
            msMainDeptEntity.setDeptName(msDeptEntity.getDeptName());
            msMainDeptEntity.setDeptSimpname(msDeptEntity.getDeptSimpname());
            msMainDeptEntity.setOrgId(msDeptEntity.getOrgId());
            msMainDeptEntity.setCreateDate(msDeptEntity.getCreateDate());
            msMainDeptEntity.setOrd(msDeptEntity.getOrd());
            msMainDeptEntity.setState(msDeptEntity.getState());
            logger.info("插入主表部门[{}]的记录",msMainDeptEntity.getDeptId());
            msMainDeptService.saveEntity(msMainDeptEntity);
        }
        //******************* 部门 结束 *******************

        //******************* 组织 开始 *******************
        for(MsOrgEntity msOrgEntity : msOrgEntityList){
            for(MsMainOrgEntity msMainOrgEntity : msMainOrgEntityList){
                if(msOrgEntity.getOrgId().equals(msMainOrgEntity.getOrgId())){
                    //修改
                    msMainOrgEntity.setParentOrgId(msOrgEntity.getParentOrgId());
                    msMainOrgEntity.setOrgCode(msOrgEntity.getOrgCode());
                    msMainOrgEntity.setOrgName(msOrgEntity.getOrgName());
                    msMainOrgEntity.setOrgSimpname(msOrgEntity.getOrgSimpname());
                    msMainOrgEntity.setOrgDblink(msOrgEntity.getOrgDblink());
                    msMainOrgEntity.setOrgLevel(msOrgEntity.getOrgLevel());
                    msMainOrgEntity.setCreateDate(msOrgEntity.getCreateDate());
                    msMainOrgEntity.setOrd(msOrgEntity.getOrd());
                    msMainOrgEntity.setState(msOrgEntity.getState());
                    logger.info("修改主表组织[{}]的记录",msMainOrgEntity.getOrgId());
                    msMainOrgService.updateByEntity(msMainOrgEntity);
                    msOrgEntityListTemp.remove(msOrgEntity);
                    msMainOrgEntityListTemp.remove(msMainOrgEntity);
                }
            }
        }

        for(MsMainOrgEntity msMainOrgEntity : msMainOrgEntityListTemp){
            //逻辑删除
            msMainOrgEntity.setState(1);
            logger.info("删除主表组织[{}]的记录",msMainOrgEntity.getOrgId());
            msMainOrgService.updateByEntity(msMainOrgEntity);
        }

        MsMainOrgEntity msMainOrgEntity = null;
        for(MsOrgEntity msOrgEntity : msOrgEntityListTemp){
            //插入
            msMainOrgEntity = new MsMainOrgEntity();
            msMainOrgEntity.setOrgId(msOrgEntity.getOrgId());
            msMainOrgEntity.setParentOrgId(msOrgEntity.getParentOrgId());
            msMainOrgEntity.setOrgCode(msOrgEntity.getOrgCode());
            msMainOrgEntity.setOrgName(msOrgEntity.getOrgName());
            msMainOrgEntity.setOrgSimpname(msOrgEntity.getOrgSimpname());
            msMainOrgEntity.setOrgDblink(msOrgEntity.getOrgDblink());
            msMainOrgEntity.setOrgLevel(msOrgEntity.getOrgLevel());
            msMainOrgEntity.setCreateDate(msOrgEntity.getCreateDate());
            msMainOrgEntity.setOrd(msOrgEntity.getOrd());
            msMainOrgEntity.setState(msOrgEntity.getState());
            logger.info("插入主表组织[{}]的记录",msMainOrgEntity.getOrgId());
            msMainOrgService.saveEntity(msMainOrgEntity);
        }
        //******************* 组织 结束 *******************

        //******************* 人员 开始 *******************
        for(MsPersonEntity msPersonEntity : msPersonEntityList){
            for(MsMainPersonEntity msMainPersonEntity : msMainPersonEntityList){
                if(msPersonEntity.getPersonId().equals(msMainPersonEntity.getPersonId())){
                    //修改
                    msMainPersonEntity.setPersonName(msPersonEntity.getPersonName());
                    msMainPersonEntity.setDeptId(msPersonEntity.getDeptId());
                    msMainPersonEntity.setOrgId(msPersonEntity.getOrgId());
                    msMainPersonEntity.setPersonDuty(msPersonEntity.getPersonDuty());
                    msMainPersonEntity.setPersonOfficial(msPersonEntity.getPersonOfficial());
                    msMainPersonEntity.setCreateDate(msPersonEntity.getCreateDate());
                    msMainPersonEntity.setOrd(msPersonEntity.getOrd());
                    msMainPersonEntity.setState(msPersonEntity.getState());
                    logger.info("修改主表人员[{}]的记录",msMainPersonEntity.getPersonId());
                    msMainPersonService.updateByEntity(msMainPersonEntity);
                    msPersonEntityListTemp.remove(msPersonEntity);
                    msMainPersonEntityListTemp.remove(msMainPersonEntity);
                }
            }
        }

        for(MsMainPersonEntity msMainPersonEntity : msMainPersonEntityListTemp){
            //逻辑删除
            msMainPersonEntity.setState(1);
            logger.info("删除主表组织[{}]的记录",msMainPersonEntity.getPersonId());
            msMainPersonService.updateByEntity(msMainPersonEntity);
        }

        MsMainPersonEntity msMainPersonEntity = null;
        for(MsPersonEntity msPersonEntity : msPersonEntityListTemp){
            //插入
            msMainPersonEntity = new MsMainPersonEntity();
            msMainPersonEntity.setPersonId(msPersonEntity.getPersonId());
            msMainPersonEntity.setPersonName(msPersonEntity.getPersonName());
            msMainPersonEntity.setDeptId(msPersonEntity.getDeptId());
            msMainPersonEntity.setOrgId(msPersonEntity.getOrgId());
            msMainPersonEntity.setPersonDuty(msPersonEntity.getPersonDuty());
            msMainPersonEntity.setPersonOfficial(msPersonEntity.getPersonOfficial());
            msMainPersonEntity.setCreateDate(msPersonEntity.getCreateDate());
            msMainPersonEntity.setOrd(msPersonEntity.getOrd());
            msMainPersonEntity.setState(msPersonEntity.getState());
            logger.info("插入主表人员[{}]的记录",msMainPersonEntity.getPersonId());
            msMainPersonService.saveEntity(msMainPersonEntity);
        }
        //******************* 人员 结束 *******************

        //******************* 用户 开始 *******************
        for(MsUserEntity msUserEntity : msUserEntityList){
            for(MsMainUserEntity msMainUserEntity : msMainUserEntityList){
                if(msUserEntity.getUserId().equals(msMainUserEntity.getUserId())){
                    //修改
                    msMainUserEntity.setUsername(msUserEntity.getUsername());
                    msMainUserEntity.setPassword(msUserEntity.getPassword());
                    msMainUserEntity.setPersonId(msUserEntity.getPersonId());
                    msMainUserEntity.setDeptId(msUserEntity.getDeptId());
                    msMainUserEntity.setOrgId(msUserEntity.getOrgId());
                    msMainUserEntity.setUserType(msUserEntity.getUserType());
                    msMainUserEntity.setCreateDate(msUserEntity.getCreateDate());
                    msMainUserEntity.setOrd(msUserEntity.getOrd());
                    msMainUserEntity.setIsAllowLogin(msUserEntity.getIsAllowLogin());
                    msMainUserEntity.setState(msUserEntity.getState());
                    logger.info("修改主表人员[{}]的记录",msMainUserEntity.getUserId());
                    msMainUserService.updateByEntity(msMainUserEntity);
                    msUserEntityListTemp.remove(msUserEntity);
                    msMainUserEntityListTemp.remove(msMainUserEntity);
                }
            }
        }

        for(MsMainUserEntity msMainUserEntity : msMainUserEntityListTemp){
            //逻辑删除
            msMainUserEntity.setState(1);
            logger.info("删除主表组织[{}]的记录",msMainUserEntity.getUserId());
            msMainUserService.updateByEntity(msMainUserEntity);
        }

        MsMainUserEntity msMainUserEntity = null;
        for(MsUserEntity msUserEntity : msUserEntityListTemp){
            //插入
            msMainUserEntity = new MsMainUserEntity();
            msMainUserEntity.setUserId(msUserEntity.getUserId());
            msMainUserEntity.setUsername(msUserEntity.getUsername());
            msMainUserEntity.setPassword(msUserEntity.getPassword());
            msMainUserEntity.setPersonId(msUserEntity.getPersonId());
            msMainUserEntity.setDeptId(msUserEntity.getDeptId());
            msMainUserEntity.setOrgId(msUserEntity.getOrgId());
            msMainUserEntity.setUserType(msUserEntity.getUserType());
            msMainUserEntity.setCreateDate(msUserEntity.getCreateDate());
            msMainUserEntity.setOrd(msUserEntity.getOrd());
            msMainUserEntity.setIsAllowLogin(msUserEntity.getIsAllowLogin());
            msMainUserEntity.setState(msUserEntity.getState());
            logger.info("插入主表用户[{}]的记录",msMainUserEntity.getUserId());
            msMainUserService.saveEntity(msMainUserEntity);
        }
        //******************* 用户 结束 *******************
        //**************************************** 主表和同步表作比较 结束 ****************************************
        logger.info("业务表同步数据成功！[{}]",getNowTime());
    }

    public String getNowTime(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String nowTime = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
        return  nowTime;
    }
}
