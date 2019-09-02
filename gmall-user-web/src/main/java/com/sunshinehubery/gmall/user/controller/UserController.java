package com.sunshinehubery.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunshinehubery.gmall.bean.UmsMember;
import com.sunshinehubery.gmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/1 12:20
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Reference
    UserService userService;

    @RequestMapping("/all")
    @ResponseBody
    public List<UmsMember> getAllMember(){
        List<UmsMember> umsMembers = userService.getAllMember();
        return umsMembers;
    }
}
