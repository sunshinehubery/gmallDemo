package com.sunshinehubery.gmall.user.mapper;

import com.sunshinehubery.gmall.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/1 12:29
 * @Version: 1.0
 **/
public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllMember();
}
