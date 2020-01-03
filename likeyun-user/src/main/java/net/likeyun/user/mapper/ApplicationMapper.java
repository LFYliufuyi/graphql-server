package net.likeyun.user.mapper;

import net.likeyun.user.entity.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationMapper {
    /**
     * 根据角色id查应用列表
     * @param roleId 角色id
     * @return 应用列表
     */
    List<Application> findApplicationByRoleId(Integer roleId);

    /**
     * 根据用户id查应用列表
     * @param userId 角色id
     * @return 应用列表
     */
    List<Application> findApplicationByUserId(Integer userId);

    /**
     * 查应用列表
     * @return 应用列表
     */
    List<Application> applications();
}
