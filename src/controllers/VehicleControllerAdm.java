package controllers;

import interfaces.VehicleListenerAdm;
import java.sql.*;
import java.util.Vector;

import daoImpl.VehicleDaoImpl;

public class VehicleControllerAdm implements VehicleListenerAdm{

	private VehicleDaoImpl dao = new VehicleDaoImpl();
		
	public VehicleControllerAdm() {
	}

   /**
	* Calls DAO to get data for all vehicles
	* @return Vehicle data split into multidimensional vector
	*/
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

   /**
	* Calls DAO to update selected vehicle
	* @param id 		Vehicle ID
	* @param make		Vehicle make
	* @param model		Vehicle model
	* @param year		Vehicle year
	* @param price 		Vehicle price
	* @param available	Vehicle availability
	* @param type		Vehicle type
	*/
	@Override
	public void updatePerformed(int id, String make, String model, int year, double price, int available, String type) {
		System.out.println("Update event received: "+id+"; "+type+"; "+make+"; "+model+"; "+year+"; "+price+"; "+available);

		dao.updateVehicleAdm(id, make, model, year, price, available, type);
	}

   /**
	* Calls DAO to delete selected vehicle
	* @param id Vehicle's ID
	*/
	@Override
	public void deletePerformed(int id) {
		System.out.println("Display event received: "+id);

		dao.deleteVehicleAdm(id);
	}

   /**
	* Calls DAO to add vehicle into DB
	* @param make 	Vehicle make
	* @param model 	Vehicle model
	* @param year 	Vehicle year
	* @param price 	Vehicle price
	* @param type	Vehicle type
	*/
	@Override
	public void addPerformed(String make, String model, int year, double price, String type) throws SQLException {
		System.out.println("Display event received: "+make+"; "+model+"; "+type);

		dao.insertVehicleAdm(make, model, year, price, type);
	}
}

