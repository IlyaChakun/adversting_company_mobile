package by.iba.service;

import by.iba.entity.user.UserRole;

public interface UserRolesService {

    UserRole findByName(String login);

}
