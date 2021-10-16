package hnt.com.service;

import hnt.com.entity.User;

public interface UserService {
    User getUserInformation(String name, String password);
}
