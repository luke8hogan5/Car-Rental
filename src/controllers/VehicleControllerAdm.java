package controllers;

import database.Database;
import interfaces.VehicleListenerAdm;
import views.VehicleViewAdm;

import java.sql.*;
import java.util.Vector;

import daoImpl.VehicleDaoImpl;

public class VehicleControllerAdm implements VehicleListenerAdm{
	
	private VehicleViewAdm view;
	private VehicleDaoImpl dao = new VehicleDaoImpl();
		
	public VehicleControllerAdm(VehicleViewAdm view) {
		this.view = view;
	}

	@Override
	public Vector<Vector<Object>> getVehicles() throws SQLException {

		ResultSet rs = dao.getAllVehiclesAdm();
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

		dao.updateVehicleAdm(id, make, model, year, price, available, type);
	}
	@Override
	public void deletePerformed(int id) throws SQLException {
		System.out.println("Display event received: "+id);

		dao.deleteVehicleAdm(id);
	}
	@Override
	public void addPerformed(String make, String model, int year, double price, String type) throws SQLException {
		System.out.println("Display event received: "+make+"; "+model+"; "+type);

		dao.insertVehicleAdm(make, model, year, price, type);
	}
}

