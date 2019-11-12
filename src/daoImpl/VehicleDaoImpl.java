package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.VehicleDao;
import database.Database;
import models.VehicleModelAdm;

public class VehicleDaoImpl implements VehicleDao {

	private VehicleModelAdm extractUserInfo(ResultSet rs) throws SQLException {
		VehicleModelAdm vehicle = new VehicleModelAdm();
		vehicle.setVehicleId( rs.getInt("vehicle_id") );
		vehicle.setVehicleMake( rs.getString("vehicleMake") );
		vehicle.setVehicleModel( rs.getString("vehicleModel") );
		vehicle.setVehicleYear( Integer.parseInt(rs.getString("vehicleYear") ));
		vehicle.setVehiclePrice( Double.parseDouble(rs.getString("vehiclePrice") ));
		vehicle.setVehicleAvailability( Integer.parseInt(rs.getString("isAvailable") ));
		vehicle.setVehicleType( rs.getString("vehicleType") );
	    return vehicle;
	}
	
	@Override
	public VehicleModelAdm getVehicle() {
		Connection conn = Database.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM vehicle;");
			if(rs.next()){
				extractUserInfo(rs);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}

	@Override
	public List<VehicleModelAdm> getAllVehicles() {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM vehicle");
	        List<VehicleModelAdm> vehicles = new ArrayList<>();
	        while(rs.next())
	        {
	        	VehicleModelAdm vehicle = extractUserInfo(rs);
	        	vehicles.add(vehicle);
	        }
	        return vehicles;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

	@Override
	public void insertVehicle(VehicleModelAdm vehicle) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO vehicle VALUES (NULL, ?, ?, ?, ?, ?, ?)");
	        ps.setString(1, vehicle.getVehicleModel());
	        ps.setString(2, vehicle.getVehicleMake());
	        ps.setInt(3, vehicle.getVehicleYear());
	        ps.setDouble(4, vehicle.getVehiclePrice());
	        ps.setInt(5, vehicle.getVehicleAvailability());
	        ps.setString(6, vehicle.getVehicleType());
	        ps.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
		
	}

	@Override
	public void updateVehicle(VehicleModelAdm vehicle) {
	    Connection conn = Database.getConnection();
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE vehicle SET vehicleModel=?, vehicleMake=?, vehicleYear=? , vehiclePrice=?, isAvailable=?, vehicleType=? WHERE vehicle_id=?");
	        ps.setString(1, vehicle.getVehicleModel());
	        ps.setString(2, vehicle.getVehicleMake());
	        ps.setInt(3, vehicle.getVehicleYear());
	        ps.setDouble(4, vehicle.getVehiclePrice());
	        ps.setInt(5, vehicle.getVehicleAvailability());
	        ps.setString(6, vehicle.getVehicleType());
	        ps.setInt(7, vehicle.getVehicleId());
	        ps.executeUpdate();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}

	@Override
	public void deleteVehicle(int vehicleId ) {
	    Connection conn = Database.getConnection();
	    try {
	        Statement st = conn.createStatement();
	        st.executeUpdate("DELETE FROM vehicle WHERE vehicle_id=" + vehicleId);

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}


}
