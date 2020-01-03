package net.likeyun.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 应用
 * @Author: lfy
 * @Date: 2019/12/31 10:20
 */
@Setter
@Getter
@ToString
public class Application {
    private Integer id;

    private String name;

    private Byte status;

    private List<Role> roles = new ArrayList<>();
}
