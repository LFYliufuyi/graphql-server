package net.likeyun.user.permission;

import lombok.extern.slf4j.Slf4j;
import net.likeyun.base.entity.Audience;
import net.likeyun.base.error_result.GraphQLErrorResult;
import net.likeyun.base.exception.GraphQLCustomException;
import net.likeyun.base.redis.RedisUtil;
import net.likeyun.base.util.JwtTokenUtil;
import net.likeyun.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2019/12/25 10:47
 */

@Aspect
@Component("LoginPermissionAspect")
@Slf4j
public class LoginPermissionAspect {
    @Resource
    private Audience audience;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private HttpServletRequest request;


    @Around("@annotation(loginPermission)")
    public Object around(ProceedingJoinPoint joinPoint, LoginPermission loginPermission) throws Throwable {
//        invoke();
        return joinPoint.proceed();
    }

    public void invoke(){
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new GraphQLCustomException(GraphQLErrorResult.USER_NOT_LOGGED_IN);
        }

        // 获取token
        final String token = authHeader.substring(7);

        if (audience == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token, audience.getBase64Secret());

        //从token中获取用户名
        String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
        Integer userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());

        try {
            //判断redis中的获取的token是否为空或者用户携带的token是否跟redis中的一致
            if (redisUtil.get(username) == null || !redisUtil.get(username).equals(token)) {
                log.info("### 登录状态过期 ###");
                throw new GraphQLCustomException(GraphQLErrorResult.PERMISSION_EXPIRE);
            }
        } catch (RedisConnectionFailureException exception) {
            log.info("### redis出错 ###");
            throw new GraphQLCustomException(GraphQLErrorResult.REDIS_ERROR);
        }
        User.setLocalUserId(userId);
    }
}
