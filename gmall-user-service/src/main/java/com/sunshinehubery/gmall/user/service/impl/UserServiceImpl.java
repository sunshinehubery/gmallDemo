package com.sunshinehubery.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunshinehubery.gmall.bean.UmsMember;
import com.sunshinehubery.gmall.service.UserService;
import com.sunshinehubery.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/1 12:23
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<UmsMember> getAllMember() {
        return userMapper.selectAll();
    }
}
