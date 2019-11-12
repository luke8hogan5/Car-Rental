import java.awt.*;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import controllers.CatalogController;
import controllers.LoginController;
import controllers.ProfileController;
import controllers.RegisterController;
import database.Database;
import interfaces.ProfileListener;
import views.*;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
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
				
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


