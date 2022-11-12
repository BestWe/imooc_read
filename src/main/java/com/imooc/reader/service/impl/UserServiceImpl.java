package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.User;
import com.imooc.reader.mapper.UserMapper;
import com.imooc.reader.service.UserService;
import com.imooc.reader.service.exception.BussinessException;
import com.imooc.reader.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param user 登录用户信息
     * @return 用户信息
     */
    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);
        if (dbUser==null){
            throw new BussinessException("M02","用户不存在");
        }
        String md5 = MD5Utils.md5Digest(user.getPassword(),dbUser.getSalt());
        if (!md5.equals(dbUser.getPassword())){
            throw new BussinessException("M03", "输入密码有误");
        }
        return dbUser;
    }
}
