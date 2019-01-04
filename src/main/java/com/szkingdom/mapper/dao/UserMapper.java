package com.szkingdom.mapper.dao;

import com.szkingdom.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lange
 * @date 2018-11-05 11:15
 */
public interface UserMapper {

    List<Users> listUser();

    void addUser(Users users);

    Users findByUsername(@Param("username") String username);

    List<Users> listUserByRoleId(@Param("roleId") Long roleId);
}
