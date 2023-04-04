package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service class for managing user operations
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Loads the user by username
     * @param username
     * @return user details
     * @throws UsernameNotFoundException if invalid username
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
    }

    /**
     * Gets a list of all users.
     * @return the list of users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Gets a user
     * @param id the ID of the user
     * @return a single user or null
     */
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new user
     * @param user user to add
     * @return the added user or null if username already exist
     */
    public User addUser(User user) {
        List<User> allUser = getAllUsers();
        for (User existingUser : allUser) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                return null;
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Updates a user
     * @param id the ID of the user
     * @param updatedUser the updated user
     * @return the updated user
     */
    public User updateUser(Integer id, User updatedUser) {
        User targetedUser = getUserById(id);

        if (updatedUser != null) {
            targetedUser.setId(id);
            targetedUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));

            return userRepository.save(targetedUser);
        } else {
            return updatedUser;
        }
    }

    /**
     * Deletes a user
     * @param id the ID of the user
     * @return the list of all users
     */
    public List<User> deleteUser(Integer id) {
        User targetedUser = getUserById(id);
        if (targetedUser != null) {
            userRepository.deleteById(id);
        }
        return getAllUsers();
    }
}
