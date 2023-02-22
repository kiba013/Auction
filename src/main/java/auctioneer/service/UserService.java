package auctioneer.service;

import auctioneer.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserByID(Long id);

    User getUserByEmail(String email);

    void saveUser(User user);

    User updateUser(Long id, User user);

    void removeUser(Long id);

}
