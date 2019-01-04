package com.szkingdom.service.impl;

import com.szkingdom.entity.Users;
import com.szkingdom.mapper.dao.UserMapper;
import com.szkingdom.service.interfaces.UserService;
import com.szkingdom.utils.CommonUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lange
 * @date 2018-12-07 18:02
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Users> listUser() {
        return userMapper.listUser();
    }

    @Override
    public void addUser(Users users) {
        users.setUserId(CommonUtils.generatePK());
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        users.setSalt(salt);
        users.setPassword(CommonUtils.md5Password(users.getPassword(), users.getUsername() + salt));
        userMapper.addUser(users);
    }

    @Override
    public Users findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
