package com.szkingdom.service.interfaces;

import com.szkingdom.entity.Users;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-07 15:30
 */
public interface UserService {
    List<Users> listUser();

    void addUser(Users users);

    Users findByUsername(String username);
}
