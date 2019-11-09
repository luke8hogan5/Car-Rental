package controllers;

import database.Database;
import interfaces.CatalogListener;
import views.CatalogView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String stmt = "SELECT vehicleMake,vehicleModel,vehiclePrice,vehicleYear FROM vehicle;";
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
        while(rs.next()){
            /** Get data from col for current row */
            Vector<Object> resultLine = new Vector<>();
            resultLine.add(rs.getString("vehicleMake"));
            resultLine.add(rs.getString("vehicleModel"));
            resultLine.add(rs.getString("vehicleYear"));
            resultLine.add(rs.getString("vehiclePrice"));
            /**  Add row */
            resultList.add(resultLine);
        }
        return resultList;
    }

}
