package com.company.controller;

import com.company.view.UserView;
import com.company.model.User;
import com.company.dao.UserDao;

public class UserController {
	private UserView view;

    private UserDao userDao = new UserDao();

    public UserController(UserView view) {
        this.view = view;
    }

    public void registerUser() {
        User user = view.getUserInput();
        userDao.saveUser(user);
        view.displayUserRegistered();
    }
    
    public void loginUser() {
        User user = view.getUserLogin();

        boolean isAuthenticated = userDao.authenticateUser(user.getUsername(), user.getPassword());

        if (isAuthenticated) {
            view.displayLoginSuccess();
        } else {
            view.displayLoginFailure();
        }
    }
}
