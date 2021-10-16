package hnt.com.service;

import hnt.com.entity.User;
import hnt.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserInformation(String name, String password) {
        User user = userRepository.findByNameAndPassword(name, password);
        return user;
    }
}
