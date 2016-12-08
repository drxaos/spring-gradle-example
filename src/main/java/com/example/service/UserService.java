package com.example.service;

import com.example.db.User;
import com.example.db.UserRepository;
import com.example.exception.ServiceWrongArgsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ManagedResource
public class UserService implements UserDetailsService, SaltSource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BasePasswordEncoder passwordEncoder;

    public User getUser(String login) {
        List<User> result = userRepository.findByLogin(login);
        return result.size() > 0 ? result.get(0) : null;
    }

    public User createUser(String login, String password) {
        User user = new User(login, "", false);
        userRepository.save(user);
        setUserPassword(login, password);
        return user;
    }

    public User createAdmin(String login, String password) {
        User user = new User(login, "", true);
        userRepository.save(user);
        setUserPassword(login, password);
        return user;
    }

    public List<User> listUsers() {
        List<User> list = new ArrayList<User>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    @ManagedOperation
    public List<String> getLogins() {
        return listUsers().stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Transactional
    @ManagedOperation
    public void setUserPassword(String login, String password) {
        User user = getUser(login);
        if (user == null) {
            throw new ServiceWrongArgsException("unknown user: " + login);
        }
        String hash = passwordEncoder.encodePassword(password, "" + Math.random() + "" + System.currentTimeMillis());
        user.setPassword(hash);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("" + username);
        }
        return user;
    }

    @Override
    public Object getSalt(UserDetails user) {
        if (user instanceof User) {
            return user.getPassword().split("\\!")[1];
        }
        return "";
    }
}
