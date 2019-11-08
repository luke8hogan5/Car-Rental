package views;

import javax.swing.*;
import javax.swing.table.JTableHeader;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import database.Database;
import interfaces.CatalogListener;
import models.CatalogModel;

public class CatalogView1 extends JFrame {
	
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private JTable tabDemo;
	private JButton btnShow;
	private CatalogListener catalogListener;
	
	public CatalogView1(){
		super("Catalog view");
		initWindow();
	}
		
	public void initWindow() {

		this.setSize(330,400);
		this.setLayout(null);
		this.setLocation(100,50);
		
		this.scpDemo = new JScrollPane();
		this.scpDemo.setBounds(10,50,300,270);
		this.btnShow = new JButton("Display Catalog");
		this.btnShow.setBounds(10,10,300,30);
		
		this.btnShow.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		btnShow_ActionPerformed(ae);
		}
		});
		
		add(this.scpDemo);
		add(this.btnShow);
		
		this.setVisible(true);
		}
		
	
	
		public void btnShow_ActionPerformed(ActionEvent ae){
		
		try{
			
			
		Connection conn = Database.getConnection();
		String sql = "SELECT vehicleMake,vehicleModel,vehiclePrice,vehicleYear FROM vehicle;";
		//vehicleMake, vehiclePrice,vehicelModel,vehicleYear
		PreparedStatement st = conn.prepareStatement(sql);
		//Statement st = conn.createStatement(sql);
	    ResultSet rs = st.executeQuery();	
									
		int count = 0;
		while(rs.next()){
			System.out.println(count);
		count++;
		}
		rs = st.executeQuery();
		
		Object[][] info = new Object[count][4];//4
		count = 0;
		while(rs.next()){
		info[count][0] = rs.getString("vehicleMake");

		info[count][1] = rs.getString("vehicleModel");
		info[count][2] = Double.valueOf( rs.getDouble("vehiclePrice") );
		info[count][3] = Integer.valueOf(rs.getInt("vehicleYear"));
		count++;
		//System.out.println(rs.getString("vehicleMake"));
		}
		
		
		String[] title = {"VehicleMake","vehicleModel","vehiclePrice","vehicleYear"};
		
		this.tabDemo = new JTable(info,title);
		
		this.jth = this.tabDemo.getTableHeader();
		
		this.scpDemo.getViewport().add(tabDemo); 
		
		
		
		}catch(SQLException sqle){
		JOptionPane.showMessageDialog(null,"Data Error","Error",JOptionPane.ERROR_MESSAGE);
		}
		}
		
		public void setCatalogListener(CatalogListener catalogListener) {
			this.catalogListener = catalogListener;
		}
		public void fireCatalogEvent(CatalogModel event) throws SQLException {
			if (catalogListener!= null) {
				catalogListener.CatalogPerformed(event);
			}
		}
}




