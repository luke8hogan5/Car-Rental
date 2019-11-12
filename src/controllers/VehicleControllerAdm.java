package controllers;

import database.Database;
import interfaces.VehicleListenerAdm;
import views.VehicleViewAdm;

import java.sql.*;
import java.util.Vector;

public class VehicleControllerAdm implements VehicleListenerAdm{
	
	private VehicleViewAdm view;
		
	public VehicleControllerAdm(VehicleViewAdm view) {
		this.view = view;
	}

	@Override
	public Vector<Vector<Object>> getVehicles() throws SQLException {
		Connection conn = Database.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery("select * from vehicle;");

		Vector<Vector<Object>> data = new Vector<>();
		while (rs.next()) {

			Vector<Object> row = new Vector<>();
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
				row.add(rs.getString(i));

			}
			data.add(row);
		}

		return data;
	}

	@Override
	public void updatePerformed(int id, String make, String model, int year, double price, int available, String type) throws SQLException {
		System.out.println("Update event received: "+id+"; "+type+"; "+make+"; "+model+"; "+year+"; "+price+"; "+available);

		Connection con = Database.getConnection();
		String sql = "UPDATE `vehicle` "
				+"SET vehicleMake='"+make+"',vehicleModel='"+model+"',vehicleYear='"+year+"',vehiclePrice='"+price+"',isAvailable='"+available+"'"
				+", vehicleType= '"+type+"' WHERE vehicle_id='"+id+"'";
		Statement st = con.createStatement();
		st.execute(sql);
	}
	@Override
	public void deletePerformed(int id) throws SQLException {
		System.out.println("Display event received: "+id);

		Connection con = Database.getConnection();
		String sql = "DELETE FROM `vehicle` WHERE vehicle_id='" +id+"'";
		Statement st = con.createStatement();
		st.execute(sql);
	}
	@Override
	public void addPerformed(String make, String model, int year, double price, String type) throws SQLException {
		System.out.println("Display event received: "+make+"; "+model+"; "+type);
		Connection con = Database.getConnection();
		String sql = "INSERT INTO vehicle(vehicleMake, vehicleModel, vehicleYear, vehiclePrice, isAvailable) VALUES (?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, make);
		ps.setString(2, model);
		ps.setInt(4, year);
		ps.setDouble(5, price);
		ps.executeUpdate();
	}
}

