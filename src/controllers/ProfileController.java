package controllers;

import database.Database;
import interfaces.ProfileListener;
import models.UserModel;

import javax.swing.*;

import daoImpl.UserDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileController implements ProfileListener {
    private JPanel view;
    private UserDaoImpl dao = new UserDaoImpl();
    
    public ProfileController(JPanel view) {
        this.view = view;
    }

    @Override
    public void profileUpdated(UserModel user) throws SQLException {
        System.out.println("Detail update received"+user.toString());

        dao.updateUserInfo(user);
    }
}
