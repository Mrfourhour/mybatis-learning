package com.xinyet.mapper;

import com.xinyet.pojo.User;

import java.util.List;

public interface UserMapper {
    User getUserById(int id);

    List<User> getAllUsers();

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(int id);
}
