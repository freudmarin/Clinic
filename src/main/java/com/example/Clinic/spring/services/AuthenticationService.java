package com.example.Clinic.spring.services;

import com.example.Clinic.spring.repository.UserRepository;
import com.example.Clinic.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.util.List;
import java.util.Optional;

import static com.example.Clinic.spring.utils.TaskConstants.SessionKeys.SESSION_ATTR_USER_LOGGED;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return false;
        } else {
            Session sess = Sessions.getCurrent();
            //  User u = new User( user.getRole(),user.getUsername(), user.getPassword());

            sess.setAttribute("userLoggedIn", user);
            sess.setAttribute("message", "Welcome  " + user.getName());
            return true;
        }
    }

    public User getUserData() {
        Session sess = Sessions.getCurrent();
        User user = (User) sess.getAttribute(SESSION_ATTR_USER_LOGGED);
        if (user == null) {
            ;
            sess.setAttribute(SESSION_ATTR_USER_LOGGED, user);
        }
        return user;
    }

    public String getUserRole(Long userID) {

        Optional<User> user = userRepository.findById(userID);
        String role = user.get().getRole();
        return role;
    }

    public void logout() {
        Session sess = Sessions.getCurrent();
        sess.removeAttribute(SESSION_ATTR_USER_LOGGED);
        sess.removeAttribute("message");
    }
}

