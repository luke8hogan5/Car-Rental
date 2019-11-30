package test;


import daoImpl.AuthenticationDaoImpl;
import daoImpl.UserDaoImpl;
import database.Database;
import models.UserModel;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;



class DatabaseTest {



	@Test
    void test() {

		
	}
	
  @Test
  void testConnection(){
      try {
    	  Connection conn = Database.getConnection();
      }
      catch(Exception e){
          e.printStackTrace();
      }
  }
  
  @Test
  void testAddUser() {
	  
	    UserDaoImpl ud = new UserDaoImpl();
	    UserModel um =  new UserModel();
	    AuthenticationDaoImpl ad = new AuthenticationDaoImpl();
	    
	    um.setName("name");
	    um.setPassword("password");
	    um.setEmail("email");
	   // um.setUserType(1000);
	   // ud.insertUser(um);
	    ad.registerUser(um.getName(),um.getPassword(),um.getEmail());
	        
        System.out.println("data generated");
  }
  /*@Test
  public void testGetUserByNamePassword() {
      UserModel um = ud.getUserByUserNameAndPassword("name","password" );
      System.out.println(um);
  }*/
  
  @Test
  void testgetAllUsers() {
	  UserDaoImpl ud = new UserDaoImpl();
	  ResultSet ums = ud.getAllUsers();
      System.out.println(ums);
  }
  
  //update
  @Test
  void testupdateUser() {
	  UserDaoImpl ud = new UserDaoImpl();
	  UserModel um =  new UserModel();

	    um.setName("name");
	    um.setPassword("password");
	    um.setEmail("email something new here");
	    um.setUserId(1000);
        ud.updateUserAdm(um.getName(),um.getEmail(),um.getUserId());
  }
  //delete datarow
 @Test
 void deleteUserById() {
	 UserDaoImpl ud = new UserDaoImpl();
      ud.deleteUserAdm(1000);
  }

}
