package dao;

import java.util.List;

import models.UserModel;

public interface UserDao {

	UserModel getUser();
	List<UserModel> getAllUsers();
	UserModel getUserByUserNameAndPassword(String username, String password);
	void insertUser(UserModel user);
	void updateUser(UserModel user);
	void deleteUser(int userId);
	void updateUserInfo(UserModel user);
	
}
