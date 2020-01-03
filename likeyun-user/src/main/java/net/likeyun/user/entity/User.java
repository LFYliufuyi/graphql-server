package net.likeyun.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.likeyun.user.enum_.SexEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户
 * @Author: lfy
 * @Date: 2020/1/3 11:15
 */
@Setter
@Getter
@ToString
public class User {
    private Integer id;

    private Byte status;

    private String username;

    private String realName;

    private SexEnum sex;

    private Integer createTime;

    private Role role;

    private List<Application> applications = new ArrayList<>();

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    private static ThreadLocal<Integer> localUserId = new ThreadLocal<>();

    public static User get() {
        return threadLocal.get();
    }

    public static void set(User user) {
        threadLocal.set(user);
    }


    public static Integer getLocalUserId() {
        return localUserId.get();
    }

    public static void setLocalUserId(Integer userId) {
        localUserId.set(userId);
    }


}
