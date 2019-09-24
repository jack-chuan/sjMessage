package cn.com.sparknet.sjMessage.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sparknet.sjMessage.app.entity.RUser;
import cn.com.sparknet.sjMessage.app.service.RUserService;
import cn.com.sparknet.sjMessage.common.util.WaterUtil;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/ruser", produces = "application/json; charset=UTF-8")
@Api(value = "mybatis 实体类映射及相关信息查询", tags = "用户操作接口")
public class RUserController {
	@Autowired
	private RUserService ruserService;
    @PostMapping("/login")
    public boolean login( HttpServletRequest request,HttpServletResponse response){
    	RUser rUser = new RUser();
    	String userName = request.getParameter("username");
    	String password = request.getParameter("password");
    	String rememberme = request.getParameter("rememberme");
    	try {
    	String pwdDark = WaterUtil.getCipherText(password, "");
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwdDark);
        rUser.setUserName(userName);
        rUser.setPassword(password);
        Subject subject = SecurityUtils.getSubject();        
            subject.login(token);
            RUser rUser_ = ruserService.findRUserByUsername(userName);
            rUser_.setPassword(password);
            String deptId = ruserService.findDeptIdByUserName(userName);
            String deptName = ruserService.findDeptNameByDeptId(deptId);
            String orgId = rUser_.getOrgId();
            String orgName = ruserService.findOrgName(orgId);
            rUser_.setOrgName(orgName);
            rUser_.setDeptName(deptName);
            System.out.println("rUser_:"+rUser_.toString());
            request.getSession().setAttribute("rUser", rUser_);
            String sessiontime = ruserService.findProperty("sessiontime");
            request.getSession().setMaxInactiveInterval(Integer.parseInt(sessiontime));
            //if("1".equals(rememberme)){
            	request.getSession().setAttribute("rememberme", rememberme);           
            //}
            return true;
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("rUser", rUser);
            request.getSession().setAttribute("error", "用户名或密码错误");
            return false;
        }
    }
    
    @PostMapping("/index")
    public List<Object> index( HttpServletRequest request){
    	RUser rUser = (RUser) request.getSession().getAttribute("rUser");

    	List<Object> list = new ArrayList<>();
    	try {
        	if(rUser !=null){
            	String userName = rUser.getUserName();
            	String password = rUser.getPassword();
        		String userId = ruserService.findUserIdByUserName(userName);
        		int unReadCount = ruserService.queryUnReadCount(userId);
        		int ReadCount = ruserService.querySendCount(userId);
        		//int count = unReadCount+sendCount;
        		list.add(unReadCount);
        		list.add(ReadCount);
        		//list.add(count);
                
        	}
        	return list;
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("rUser", rUser);
            request.getSession().setAttribute("error", "用户名或密码错误");
            return list;
        }
    }
    
    @PostMapping("/unReadCount")
    public List<Object> unReadCount( HttpServletRequest request){
    	String userId = request.getParameter("userId");
    	RUser rUser = ruserService.findPasswordByUserId(userId);
    	List<Object> list = new ArrayList<>();
    	try {
        	if(rUser !=null){
        		int unReadCount = ruserService.queryUnReadCount(userId);
        		int ReadCount = ruserService.querySendCount(userId);
        		list.add(unReadCount);
        		list.add(ReadCount);
                
        	}
        	return list;
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("rUser", rUser);
            request.getSession().setAttribute("error", "用户名或密码错误");
            return list;
        }
    }
    
    @PostMapping("/quit")
    public boolean quit( HttpServletRequest request){
    	
    	try {
    		SecurityUtils.getSubject().logout();
    		request.getSession().removeAttribute("rUser");
    		request.getSession().removeAttribute("rememberme");
    		return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    @PostMapping("/getPassword")
    public RUser getPassword( HttpServletRequest request){
    	
    	try {
    		RUser rUser = (RUser)request.getSession().getAttribute("rUser");
    		String rememberme = (String)request.getSession().getAttribute("rememberme");
    		if(rUser !=null&&"1".equals(rememberme)){
    			
    			return rUser;
    			
    		}else{
    			return null;
    		}
    		
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
   
    @GetMapping("/loginByUserId")
    public boolean loginByUsername( HttpServletRequest request,HttpServletResponse response){
    	String userId = request.getParameter("userId");
        RUser rUser_ = null;
    	try {
            rUser_ = ruserService.findPasswordByUserId(userId);
            if(rUser_!=null){
                String userName = rUser_.getUserName();
                String password = rUser_.getPassword();
                UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                String deptId = ruserService.findDeptIdByUserName(userName);
                String deptName = ruserService.findDeptNameByDeptId(deptId);
                String orgId = rUser_.getOrgId();
                String orgName = ruserService.findOrgName(orgId);
                rUser_.setOrgName(orgName);
                rUser_.setDeptName(deptName);
                request.getSession().setAttribute("rUser", rUser_);
                //单点登录到首页
                //response.sendRedirect("/index");
                response.sendRedirect("/pageJump/toListPage?iframe=receiveMsg");
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("rUser", rUser_);
            request.getSession().setAttribute("error", "用户名或密码错误");
            return false;
        }
        return false;
    }
    
    @GetMapping("/loginWriteMessagePage")
    public void loginWriteMessagePage( HttpServletRequest request,HttpServletResponse response){
    	String userId = request.getParameter("userId");
    	RUser rUser_ = ruserService.findPasswordByUserId(userId);
    	try {
	        if(rUser_!=null){
	        	String userName = rUser_.getUserName();
	        	String password = rUser_.getPassword();
	        	UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
	            Subject subject = SecurityUtils.getSubject();            
	            subject.login(token);
	            String deptId = ruserService.findDeptIdByUserName(userName);
	            String deptName = ruserService.findDeptNameByDeptId(deptId);
	            String orgId = rUser_.getOrgId();
	            String orgName = ruserService.findOrgName(orgId);
	            rUser_.setOrgName(orgName);
	            rUser_.setDeptName(deptName);
	            request.getSession().setAttribute("rUser", rUser_);
	            //单点登录到首页
	            //response.sendRedirect("/index");
	            response.sendRedirect("/pageJump/toListPage?iframe=toWriteMessagePage");
	        }
        }catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("rUser", rUser_);
            request.getSession().setAttribute("error", "用户名或密码错误");
        }
    }
    
    @PostMapping("/queryUser")
    public List<String> queryUser( HttpServletRequest request){
    	List<String> list = new ArrayList<>();
    	try {
	            RUser rUser = (RUser) request.getSession().getAttribute("rUser");
	            if(rUser !=null){
	            String deptId = rUser.getDeptId();
	            String personId = rUser.getPersonId();
	            String orgId = rUser.getOrgId();
	            //String deptId = ruserService.findDeptIdByUserName(username);
	            String deptName = ruserService.findDeptNameByDeptId(deptId);
	            String personName = ruserService.findPersonName(personId);
	            String orgName = ruserService.findOrgName(orgId);

	            list.add(personName);
	            list.add(deptName);
	            list.add(orgId);
	            list.add(orgName);
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
