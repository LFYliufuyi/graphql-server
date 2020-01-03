package net.likeyun.user.service;

import graphql.language.Selection;
import graphql.schema.DataFetchingEnvironment;
import net.likeyun.user.entity.Application;
import net.likeyun.user.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByUserId(Integer id, Selection selection);

    List<Role> findRolesByApplicationId(Integer applicationId, Selection selection);

    List<Role> roles(DataFetchingEnvironment environment);
}
