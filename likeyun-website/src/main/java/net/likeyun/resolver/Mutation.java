package net.likeyun.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import net.likeyun.user.service.LoginService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: 增删改
 * @Author: lfy
 * @Date: 2019/12/20 14:13
 */
@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    @Resource
    private LoginService loginService;

    public String login(String username,String password){

        return loginService.login(username,password);
    }
}
