package dao;

import models.UserModel;

import java.sql.SQLException;

public interface AuthenticationDao {

	UserModel validateLogin(String name, String password);

	void registerUser(String username, String password, String email);

	UserModel loginAfterRegister()throws SQLException;

}
