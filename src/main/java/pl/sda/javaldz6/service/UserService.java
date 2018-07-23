package pl.sda.javaldz6.service;

import pl.sda.javaldz6.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    void addUser(String name, int age);

    User getUserById(int index);

    User modifyUser(int index, User user);

    List<User> getAll();

    void init();

    void close();
}
