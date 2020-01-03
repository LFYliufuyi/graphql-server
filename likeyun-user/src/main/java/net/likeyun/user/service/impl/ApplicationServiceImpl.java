package net.likeyun.user.service.impl;

import graphql.language.Field;
import graphql.language.Selection;
import graphql.language.SelectionSet;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import net.likeyun.user.entity.Application;
import net.likeyun.user.entity.Role;
import net.likeyun.user.mapper.ApplicationMapper;
import net.likeyun.user.service.ApplicationService;
import net.likeyun.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2020/1/3 15:25
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Resource
    private ApplicationMapper applicationMapper;

    @Resource
    private RoleService roleService;

    private List<Application> getApplications(Selection selection, List<Application> applications) {
        SelectionSet selectionSet = (SelectionSet) selection.getChildren().get(0);
        List<Selection> selections = selectionSet.getSelections();
        for (Selection selection1 : selections) {
            Field f = (Field) selection1;
            if (f.getName().equals("roles")) {
                for (Application application : applications) {
                    List<Role> roles = roleService.findRolesByApplicationId(application.getId(), selection1);
                    application.setRoles(roles);
                }
            }
        }
        return applications;
    }

    @Override
    public List<Application> findApplicationByRoleId(Integer roleId, Selection selection) {
        List<Application> applications = applicationMapper.findApplicationByRoleId(roleId);
        return getApplications(selection, applications);
    }


    @Override
    public List<Application> findApplicationByUserId(Integer userId, Selection selection) {
        List<Application> applications = applicationMapper.findApplicationByUserId(userId);
        return getApplications(selection, applications);
    }

    @Override
    public List<Application> applications(DataFetchingEnvironment environment) {
        List<Application> applications = applicationMapper.applications();
        Selection selection = environment.getField();
        return getApplications(selection, applications);
    }
}
