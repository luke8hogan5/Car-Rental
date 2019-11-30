package controllers;

import daoImpl.VehicleDaoImpl;
import interfaces.CatalogListener;
import models.VehicleModel;
import views.CatalogView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CatalogController implements CatalogListener{
    private CatalogView view;
    private VehicleDaoImpl dao = new VehicleDaoImpl();

    public CatalogController(CatalogView view) {
        this.view = view;
    }

  /**
   * Calls DAO to get Catalog info
   * @return  Vehicle data split into multidimensional vector to be used in table model
   */
    @Override
    public Vector<Vector<Object>> getCatalog() throws SQLException {
        System.out.println("Attempting to get catalog data...");

        return getResults(dao.getCatalog());
    }

  /**
   * Calls DAO to get Catalog info filtered by keyword
   * @param    keyword word to filter catalog by.
   * @return  Filtered vehicle data split into multidimensional vector to be inserted into table model.
   */
    @Override
    public Vector<Vector<Object>> searchByKeyword(String keyword) throws SQLException {
        System.out.println("Searching keyword: "+keyword);

        return getResults(dao.getSearchResults(keyword));
    }

    /**
     * Extracts data from DB query and splits into multidimen array
     * @param rs contains results from database query to be extracted
     * @return split data from rs in form of multidimen array
    */
    private Vector<Vector<Object>> getResults(ResultSet rs) throws SQLException{
        Vector<Vector<Object>> resultList = new Vector<>();
        ArrayList<VehicleModel> models = new ArrayList<>();
        while(rs.next()){
            Vector<Object> resultLine = new Vector<>();
            VehicleModel model = new VehicleModel(rs.getInt("vehicle_id"),
                                                  rs.getString("vehicleType"),
                                                  rs.getString("vehicleMake"),
                                                  rs.getDouble("vehiclePrice"),
                                                  rs.getString("vehicleModel"),
                                                  rs.getInt("vehicleYear"));
            models.add(model);
            resultLine.add(model.getVehicleMake());
            resultLine.add(model.getVehicleModel());
            resultLine.add(model.getVehicleYear());
            resultLine.add(model.getVehiclePrice());
            resultLine.add("Order");
            resultList.add(resultLine);
        }

        view.setVehicleModels(models);
        return resultList;
    }

}
