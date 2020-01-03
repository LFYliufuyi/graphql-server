package net.likeyun.user.mapper;

import net.likeyun.user.entity.User;
import net.likeyun.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询用户列表
     * @return 用户列表
     */
    List<User> users();

    /**
     * 根据id查用户
     * @param userId 用户id
     * @return 用户
     */
    User user(Integer userId);

    /**
     * 根据用户名查用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo findUserInfoByUsername(String username);
}
