package dao;

import java.sql.ResultSet;
import java.util.List;

import models.UserModel;

public interface UserDao {

	ResultSet getAllUsers();
	void updateUserInfo(UserModel user);
	void updateUserAdm(String name, String email, int id);
	void deleteUserAdm(int id);
	
}
