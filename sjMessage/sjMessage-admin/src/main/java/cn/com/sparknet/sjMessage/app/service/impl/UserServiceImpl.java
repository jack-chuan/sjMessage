package cn.com.sparknet.sjMessage.app.service.impl;

import com.github.pagehelper.PageHelper;
import cn.com.sparknet.sjMessage.app.entity.User;
import cn.com.sparknet.sjMessage.app.mapper.UserMapper;
import cn.com.sparknet.sjMessage.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author leolee
* @date 2018-08-06 15:52
*/

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    

    /**
     * 添加用户
     * @author leolee
     * @date 2018-08-06 15:52
     * @param user
     * @return int
     * @throws 
     * @since 
    */
    @Override
    public int addUser(User user){
        return userMapper.insertSelective(user);
    }

    /*
    * 这个方法中用到了的分页插件pagehelper
    * 需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */


    /**
     * 查询所有用户（带分页）
     * @author leolee
     * @date 2018-08-06 15:50
     * @param pageNum
     * @param pageSize
     * @return java.util.List<cn.com.sparknet.sjMessage.app.entity.User>
    */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize){
        // Page
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }


    /**
     * 通过用户名查询用户信息
     * @author leolee
     * @date 2018-08-06 15:51
     * @param userName
     * @return cn.com.sparknet.sjMessage.app.entity.User
     * @throws 
     * @since 
    */
    @Override
    public User selectByUserName(String userName){
        return userMapper.selectByUserName(userName);
    }

    /**
     * 通过用户id查询用户信息
     * @author leolee
     * @date 2018-08-06 15:51
     * @param userId
     * @return cn.com.sparknet.sjMessage.app.entity.User
     * @throws
     * @since
     */
    @Override
    public List<HashMap> selectByUserId(String userId){
        return userMapper.selectByUserId(userId);
    }



    /**
     * 通过用户名查询用户手机
     * @author mojiayi
     * @date 2018-08-06 15:45
     * @param userName
     * @return java.lang.String
    */
    @Override
    public String selectPhoneByUserName(String userName){
        return userMapper.selectPhoneByUserName(userName);
    }
}
