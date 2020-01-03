package net.likeyun.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.likeyun.user.enum_.SexEnum;

/**
 * @Description: 用户信息
 * @Author: lfy
 * @Date: 2020/1/3 17:32
 */
@Setter
@Getter
@ToString
public class UserInfo {
    private Integer id;

    private String username;

    private String password;

    private Integer roleId;
}
