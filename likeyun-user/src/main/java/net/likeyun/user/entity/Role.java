package net.likeyun.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 角色
 * @Author: lfy
 * @Date: 2019/12/24 17:10
 */
@Setter
@Getter
@ToString
public class Role {
    private Integer id;

    private String name;

    private Byte status;

    private List<Application> applications = new ArrayList<>();
}
