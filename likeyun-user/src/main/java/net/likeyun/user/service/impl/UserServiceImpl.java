package net.likeyun.user.service.impl;

import graphql.language.Field;
import graphql.language.Selection;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import net.likeyun.user.entity.Application;
import net.likeyun.user.entity.Role;
import net.likeyun.user.entity.User;
import net.likeyun.user.entity.UserInfo;
import net.likeyun.user.mapper.UserMapper;
import net.likeyun.user.service.ApplicationService;
import net.likeyun.user.service.RoleService;
import net.likeyun.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户业务
 * @Author: lfy
 * @Date: 2019/12/25 15:39
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private ApplicationService applicationService;

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        return userMapper.findUserInfoByUsername(username);
    }

    @Override
    public List<User> users(DataFetchingEnvironment environment) {
        List<User> users = userMapper.users();
        Field field = environment.getField();
        List<Selection> selections = field.getSelectionSet().getSelections();
        for (Selection selection : selections) {
            Field f = (Field) selection;
            if (f.getName().equals("role")) {
                for (User user : users) {
                    Role role = roleService.findRoleByUserId(user.getId(), selection);
                    user.setRole(role);
                }
            } else if (f.getName().equals("applications")) {
                for (User user : users) {
                    List<Application> applications = applicationService.findApplicationByUserId(user.getId(), selection);
                    user.setApplications(applications);
                }
            }
        }
        return users;
    }

    @Override
    public User user(Integer userId, DataFetchingEnvironment environment) {
        User user = userMapper.user(userId);
        Field field = environment.getField();
        List<Selection> selections = field.getSelectionSet().getSelections();
        for (Selection selection : selections) {
            Field f = (Field) selection;
            if (f.getName().equals("role")) {
                Role role = roleService.findRoleByUserId(user.getId(), selection);
                user.setRole(role);
            } else if (f.getName().equals("applications")) {
                List<Application> applications = applicationService.findApplicationByUserId(user.getId(), selection);
                user.setApplications(applications);
            }
        }
        return user;
    }
}
