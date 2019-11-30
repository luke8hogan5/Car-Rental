package controllers;

import daoImpl.UserDaoImpl;
import interfaces.ProfileListener;
import models.UserModel;

public class ProfileController implements ProfileListener {
    private UserDaoImpl dao = new UserDaoImpl();
    
    public ProfileController() {
    }

   /**
    * Calls DAO to update user's details
    * @param user current user's details
    */
    @Override
    public void profileUpdated(UserModel user) {
        System.out.println("Detail update received"+user.toString());

        dao.updateUserInfo(user);
    }
}
