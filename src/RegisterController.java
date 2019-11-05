import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController implements RegisterListener {
	private RegisterView view;
	
	public RegisterController(RegisterView view) {
		this.view = view;
	}

	@Override
	public void registerPerformed(RegisterModel event) throws SQLException {
		System.out.println("Register event received: " + event.getName() + "; " + event.getPassword());
		//add to database
		
		String username = event.getName();
		String password = event.getPassword();
		
		String url = String.format("jdbc:mysql://sql2.freemysqlhosting.net/sql2310355", 3306);
		Connection conn = DriverManager.getConnection(url, "sql2310355", "gK5%qI5%");
		
		String query = "INSERT INTO `account`(`userName`,`userPassword`) VALUES (?,?);";
		
        PreparedStatement ps = conn.prepareStatement(query); 
		ps.setString(1, username);
	    ps.setString(2, password);
	    ps.executeUpdate(); 
	}
	
	
}    