package controllers;

import database.Database;
import interfaces.CatalogListener;
import models.VehicleModel;
import views.CatalogView;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CatalogController implements CatalogListener{
    CatalogView view;

    public CatalogController(CatalogView view) {
        this.view = view;
    }

    @Override
    public Vector<Vector<Object>> getCatalog() throws SQLException {
        System.out.println("Attempting to get catalog data...");


        Connection conn = Database.getConnection();
        String stmt = "SELECT * FROM vehicle Where isAvailable = 1;";
        PreparedStatement st = conn.prepareStatement(stmt);
        ResultSet rs = st.executeQuery();

        return getResults(rs);
    }

    @Override
    public Vector<Vector<Object>> searchByKeyword(String keyword) throws SQLException {
        System.out.println("Searching keyword: "+keyword);

        Connection conn = Database.getConnection();

        String stmt = "SELECT * FROM vehicle WHERE vehicleType LIKE ? ";
        stmt += "OR vehicleMake LIKE ? OR vehicleModel LIKE ? OR vehicleYear LIKE ? OR vehiclePrice LIKE ?;";

        PreparedStatement ps = conn.prepareStatement(stmt);
        for(int i=1; i<6; i++)
        ps.setString(i, "%"+keyword+"%");

        ResultSet rs = ps.executeQuery();

        return getResults(rs);
    }

    //
    private Vector<Vector<Object>> getResults(ResultSet rs) throws SQLException{
        Vector<Vector<Object>> resultList = new Vector<>();
        ArrayList<VehicleModel> models = new ArrayList<>();
        while(rs.next()){
            Vector<Object> resultLine = new Vector<>();
            VehicleModel model = new VehicleModel(rs.getInt("vehicle_id"),
                                                  rs.getInt("vehicleType"),
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
