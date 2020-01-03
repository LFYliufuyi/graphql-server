package net.likeyun.user.service;

import graphql.schema.DataFetchingEnvironment;
import net.likeyun.user.entity.User;
import net.likeyun.user.entity.UserInfo;

import java.util.List;

public interface UserService {

    List<User> users(DataFetchingEnvironment environment);

    User user(Integer userId,DataFetchingEnvironment environment);

    UserInfo findUserInfoByUsername(String username);
}
