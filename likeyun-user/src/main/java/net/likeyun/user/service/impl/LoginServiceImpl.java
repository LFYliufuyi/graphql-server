package net.likeyun.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.likeyun.base.entity.Audience;
import net.likeyun.base.error_result.GraphQLErrorResult;
import net.likeyun.base.exception.GraphQLCustomException;
import net.likeyun.base.redis.RedisUtil;
import net.likeyun.base.util.JwtTokenUtil;
import net.likeyun.base.util.TimeUtil;
import net.likeyun.user.entity.UserInfo;
import net.likeyun.user.service.LoginService;
import net.likeyun.user.service.UserService;
import net.likeyun.user.util.EncryptUtil;
import net.likeyun.user.util.IPUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2020/1/3 17:19
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserService userService;

    @Resource
    private Audience audience;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private HttpServletRequest request;

    @Override
    public String login(String username, String password) {
        UserInfo userInfo = userService.findUserInfoByUsername(username);
        Integer roleId = userInfo.getRoleId();
        //判断是否有此用户与密码是否正确
        if (userInfo == null || !EncryptUtil.encryptPassword(username,password,roleId).equals(userInfo.getPassword())) {
            throw new GraphQLCustomException(GraphQLErrorResult.USER_LOGIN_ERROR);
        }
        String userId = userInfo.getId().toString();
        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, roleId, audience);
        log.info("### 登录成功, token={} ###", token);
        //把token存进redis中.时间单位为秒
        redisUtil.set(username, token, 7200);

        Integer timeStamp = TimeUtil.currentTimeStamp();
        //获取登录者ip
        String ip = IPUtil.getIpAddress(request);
        //获取登录者ip所在地
        String address = IPUtil.getCityInfo(ip);
        //新建登录日志对象，设值


        return token;
    }
}
