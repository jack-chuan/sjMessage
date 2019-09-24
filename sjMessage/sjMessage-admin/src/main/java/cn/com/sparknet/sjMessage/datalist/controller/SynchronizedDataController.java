package cn.com.sparknet.sjMessage.datalist.controller;

import cn.com.sparknet.sjMessage.common.util.MapResult;
import cn.com.sparknet.sjMessage.datalist.service.impl.SynchronizedDataServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "datalist/alldata", produces = "application/json; charset=UTF-8")
@Api(value = "sjMessage Generator 自动生成接口", tags = "sjMsg-Gen：alldata")
public class SynchronizedDataController {

    private final static Logger logger = LoggerFactory.getLogger(SynchronizedDataController.class);

    @Autowired
    private SynchronizedDataServiceImpl synchronizedDataServiceImpl;

    /**
     * 所有列表
     */
    @GetMapping("/synchronizedData")
    @RequiresPermissions("datalist:alldata:synchronizedData")
    @ApiOperation(value = "alldata所有同步数据", notes = "alldata:synchronizedData")
    public MapResult synchronizedData(){
        try {
            synchronizedDataServiceImpl.dataKTL();
            return MapResult.success();
        }  catch (Exception e){
            logger.error("同步数据失败", e);
            return MapResult.error();
        }
    }
}
