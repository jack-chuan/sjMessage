package cn.com.sparknet.sjMessage.app.service;

import cn.com.sparknet.sjMessage.app.entity.User;

import java.util.List;

public interface UserService {

    int addUser(User user);
    List<User> findAllUser(int pageNum, int pageSize);
    User selectByUserName(String userName);
    String selectPhoneByUserName(String userName);
}

