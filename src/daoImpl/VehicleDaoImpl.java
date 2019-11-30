package daoImpl;

import dao.VehicleDao;
import database.Database;
import models.VehicleModel;

import java.sql.*;

public class VehicleDaoImpl implements VehicleDao {

   /**
	* Extract vehicle details from DB query results
	* @param rs Results from DB query
	* @return Vehicle data contained in a VehicleModel
	*/
	private VehicleModel extractVehicleInfo(ResultSet rs) throws SQLException {
		VehicleModel vehicle = new VehicleModel();
		vehicle.setVehicleId( rs.getInt("vehicle_id") );
		vehicle.setVehicleMake( rs.getString("vehicleMake") );
		vehicle.setVehicleModel( rs.getString("vehicleModel") );
		vehicle.setVehicleYear( Integer.parseInt(rs.getString("vehicleYear") ));
		vehicle.setVehiclePrice( Double.parseDouble(rs.getString("vehiclePrice") ));
//		vehicle.setVehicleAvailability( Integer.parseInt(rs.getString("isAvailable") ));
		vehicle.setVehicleType( rs.getString("vehicleType") );
	    return vehicle;
	}

   /**
	* Get all vehicle data from DB
	* @return Results from DB query
	*/
	@Override
	public ResultSet getAllVehiclesAdm() {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM vehicle");
	        
	        return rs;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

   /**
	* Get all data for available vehicles from DB
	* @return Results from DB query
	*/
	@Override
	public ResultSet getCatalog() {
		Connection conn = Database.getConnection();
			try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM vehicle WHERE isAvailable = 1;");
			ResultSet rs = ps.executeQuery();
			return rs;
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
			return null;
	}

   /**
	* Get data from available vehicles in DB that's been filtered by a user entered keyword
	* @param keyword User defined keyword to filter data by
	* @return Results from DB query
	*/
	@Override
	public ResultSet getSearchResults(String keyword) {
		Connection conn = Database.getConnection();
			try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM vehicle WHERE vehicleType LIKE ? OR vehicleMake LIKE ? OR vehicleModel LIKE ? OR vehicleYear LIKE ? OR vehiclePrice LIKE ? AND isAvailable = 1;");
	        
			for(int i=1; i<6; i++) {
	        	ps.setString(i, "%"+keyword+"%");
			}
	        	ResultSet rs = ps.executeQuery();
	        	return rs;
	        
		
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}
			return null;
	}

   /**
	* Insert new vehicle into DB
	*/
	@Override
	public void insertVehicleAdm(String make, String model, int year, double price, String type) {
	    Connection conn = Database.getConnection();
	    try {
			String sql = "INSERT INTO vehicle(vehicleMake, vehicleModel, vehicleYear, vehiclePrice, vehicleType) VALUES (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, make);
			ps.setString(2, model);
			ps.setInt(3, year);
			ps.setDouble(4, price);
			ps.setString(5, type);
			ps.executeUpdate();
			
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
	}

   /**
	* Update vehicle details in DB
	*/
	@Override
	public void updateVehicleAdm(int id, String make, String model, int year, double price, int available, String type) {
	    Connection conn = Database.getConnection();
	    try {
			String sql = "UPDATE `vehicle` "
					+"SET vehicleMake='"+make+"',vehicleModel='"+model+"',vehicleYear='"+year+"',vehiclePrice='"+price+"',isAvailable='"+available+"'"
					+", vehicleType= '"+type+"' WHERE vehicle_id='"+id+"'";
			Statement st = conn.createStatement();
			st.execute(sql);

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

   /**
	* Delete Vehicle from DB
	*/
	@Override
	public void deleteVehicleAdm(int vehicleId ) {
	    Connection conn = Database.getConnection();
	    try {
			String sql = "DELETE FROM `vehicle` WHERE vehicle_id='" +vehicleId+"'";
			Statement st = conn.createStatement();
			st.execute(sql);
			
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}


}
