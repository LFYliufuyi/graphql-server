package net.likeyun.user.service.impl;

import graphql.language.Field;
import graphql.language.Selection;
import graphql.language.SelectionSet;
import graphql.schema.DataFetchingEnvironment;
import net.likeyun.user.entity.Application;
import net.likeyun.user.entity.Role;
import net.likeyun.user.mapper.RoleMapper;
import net.likeyun.user.service.ApplicationService;
import net.likeyun.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2020/1/3 14:55
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private ApplicationService applicationService;

    @Override
    public Role findRoleByUserId(Integer userId, Selection selection) {
        Role role = roleMapper.findRoleByUserId(userId);
        SelectionSet selectionSet = (SelectionSet) selection.getChildren().get(0);
        List<Selection> selections = selectionSet.getSelections();
        for (Selection selection1 : selections) {
            Field f = (Field) selection1;
            if (f.getName().equals("applications")) {
               List<Application> applications = applicationService.findApplicationByRoleId(role.getId(),selection1);
                role.setApplications(applications);
            }
        }
        return role;
    }

    private List<Role> getRoles(Selection selection, List<Role> roles) {
        SelectionSet selectionSet = (SelectionSet) selection.getChildren().get(0);
        List<Selection> selections = selectionSet.getSelections();
        for (Selection selection1 : selections) {
            Field f = (Field) selection1;
            for (Role role : roles) {
                if (f.getName().equals("applications")) {
                    List<Application> applications = applicationService.findApplicationByRoleId(role.getId(), selection1);
                    role.setApplications(applications);
                }
            }

        }
        return roles;
    }

    @Override
    public List<Role> findRolesByApplicationId(Integer applicationId, Selection selection) {
        List<Role> roles = roleMapper.findRolesByApplicationId(applicationId);
        return getRoles(selection, roles);
    }


    @Override
    public List<Role> roles(DataFetchingEnvironment environment) {
        List<Role> roles = roleMapper.roles();
        Selection selection = environment.getField();
        return getRoles(selection, roles);
    }
}
