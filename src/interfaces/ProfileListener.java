package interfaces;

import models.UserModel;

import java.sql.SQLException;

public interface ProfileListener {
    void profileUpdated(UserModel user) throws SQLException;
}
