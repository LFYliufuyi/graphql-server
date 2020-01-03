package net.likeyun.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 登录日志
 * @Author: lfy
 * @Date: 2020/1/3 17:42
 */
@Setter
@Getter
@ToString
public class LoginLog {
    private Integer id;

    private String username;

    private Integer roleId;

    private String ip;

    private String address;

    private Integer loginTime;
}
