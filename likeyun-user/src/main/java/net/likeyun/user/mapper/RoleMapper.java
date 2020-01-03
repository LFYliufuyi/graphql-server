package net.likeyun.user.mapper;

import net.likeyun.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 根据用户id查角色
     * @param userId 用户id
     * @return 角色
     */
    Role findRoleByUserId(@Param("userId") Integer userId);

    /**
     * 根据应用id查角色列表
     * @param applicationId 应用id
     * @return 角色列表
     */
    List<Role> findRolesByApplicationId(Integer applicationId);

    /**
     * 查角色列表
     * @return 角色列表
     */
    List<Role> roles();
}
