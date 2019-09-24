package cn.com.sparknet.sjMessage.app.controller;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import cn.com.sparknet.sjMessage.app.service.RUserService;
import cn.com.sparknet.sjMessage.common.util.JsonResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/config", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 系统参数相关信息查询", tags = "系统参数接口")
public class ConfigController {

    @Autowired
    private RUserService ruserService;

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @PostMapping("/getConfig")
    public JsonResult getConfig(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = null;
        Map<String, Object> map = new HashMap<>();
        try {
            RUser rUser = (RUser)request.getSession().getAttribute("rUser");
            String orgId = rUser.getOrgId();
            String orgName = rUser.getOrgName();
            String download_server = ruserService.findProperty("download_server");
            String attachment_type = ruserService.findProperty("attachment_type");
            String attachment_maxsize = ruserService.findProperty("attachment_maxsize");
            map.put("orgId",orgId);
            map.put("orgName",orgName);
            map.put("download_server",download_server);
            map.put("attachment_type",attachment_type);
            map.put("attachment_maxsize",attachment_maxsize);
            jsonResult = new JsonResult(map, "200", "查询成功");
        }catch (Exception e){
            jsonResult = new JsonResult(map,"500","查询失败");
        }
        return jsonResult;
    }
}
