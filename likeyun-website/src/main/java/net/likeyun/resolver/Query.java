package net.likeyun.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import net.likeyun.user.entity.Application;
import net.likeyun.user.entity.Role;
import net.likeyun.user.entity.User;
import net.likeyun.user.permission.LoginPermission;
import net.likeyun.user.service.ApplicationService;
import net.likeyun.user.service.RoleService;
import net.likeyun.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 查询
 * @Author: lfy
 * @Date: 2019/12/19 16:54
 */
@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {
    @Resource
    private UserService userService;

    public List<User> users(DataFetchingEnvironment environment) {
        return userService.users(environment);
    }

    @LoginPermission
    public User user(Integer userId,DataFetchingEnvironment environment){
        if (userId != null) {
            return userService.user(userId,environment);
        }
        return userService.user(User.getLocalUserId(),environment);
    }

    @Resource
    private ApplicationService applicationService;

    public List<Application> applications(DataFetchingEnvironment environment){
        return applicationService.applications(environment);
    }

    @Resource
    private RoleService roleService;

    public List<Role> roles(DataFetchingEnvironment environment){
        return roleService.roles(environment);
    }


}
