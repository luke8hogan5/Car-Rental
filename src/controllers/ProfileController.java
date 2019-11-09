package controllers;

import database.Database;
import interfaces.ProfileListener;
import models.UserModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileController implements ProfileListener {
    private JFrame view;

    public ProfileController(JFrame view) {
        this.view = view;
    }

    @Override
    public void profileUpdated(UserModel user) throws SQLException {
        System.out.println("Detail update received"+user.toString());


        Connection conn = Database.getConnection();
        String stmt = "UPDATE account SET userName=?, email=?, address=? WHERE user_id=?;";

        PreparedStatement ps = conn.prepareStatement(stmt);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getAddress());
        ps.setInt(4, user.getId());
        ps.execute();
    }
}
