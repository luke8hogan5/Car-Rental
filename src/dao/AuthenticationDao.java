package dao;

import models.UserModel;

public interface AuthenticationDao {

	UserModel validateLogin(String name, String password);

	void registerUser(String username, String password, String email);

	void loginAfterRegister();

}
