import java.sql.SQLException;

import javax.swing.SwingUtilities;
import database.Database;
import views.*;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Database db = null;
			try {
				db = Database.getInstance();
			} catch (SQLException e1) {
				System.out.print("Database not initialised");
				e1.printStackTrace();
			}
			if(db != null) {
MasterView masterView = new MasterView();
			}
		});
	}
}


