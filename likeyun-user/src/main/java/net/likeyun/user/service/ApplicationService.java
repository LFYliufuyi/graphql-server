package net.likeyun.user.service;

import graphql.language.Selection;
import graphql.schema.DataFetchingEnvironment;
import net.likeyun.user.entity.Application;

import java.util.List;

public interface ApplicationService {

    List<Application> findApplicationByRoleId(Integer roleId, Selection selection);

    List<Application> findApplicationByUserId(Integer userId, Selection selection);

    List<Application> applications(DataFetchingEnvironment environment);
}
