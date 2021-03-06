package views;

import components.ClientsTableButtonRenderer;
import components.ClientsTableRenderer;
import controllers.CatalogController;
import interfaces.CatalogListener;
import models.VehicleModel;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CatalogView extends JPanel{
    private MasterView parent;
    private JTextField searchField;
    private CatalogListener listener;
    private JScrollPane contentWindow;
    private GridBagConstraints gc = new GridBagConstraints();
    private ArrayList<VehicleModel> vehicleModels;
    private boolean showCatBtnVisible = false;

    CatalogView(MasterView parent) {
        super();
        this.parent = parent;
        listener = new CatalogController(this);

        setLayout(new GridBagLayout());
        setMenu();
        buildInterface();

        try {
            getFullCatalog();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//  Setup menu with search bar and account button
    private void setMenu() {
        JButton backbtn;
    	backbtn = new JButton("Back");
        JMenuBar menuBar = new JMenuBar();
        JMenu accNav = new JMenu("Account");
        accNav.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                parent.changePanel(new ProfileView(parent));
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                /* Do nothing */
            }
            @Override
            public void menuCanceled(MenuEvent e) {
                //Do nothing
            }
        });
        Button searchBtn = new Button("Search");
        searchBtn.addActionListener(e -> {
            try {
                if(!showCatBtnVisible) {
                    returnToCatButton();
                    showCatBtnVisible = true;
                }
                fireSearchEvent();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        searchField = new JTextField(20);
        searchField.setText("Search Keyword");

        menuBar.add(accNav);
        menuBar.add(new JSeparator(), CENTER_ALIGNMENT);
        menuBar.add(searchField);
        menuBar.add(searchBtn);

        add(menuBar);
        
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 0);


        backbtn.setBounds(0,180,80,30);
        add(backbtn,gc);

        backbtn.addActionListener(ae -> parent.changePanel(new LoginView(parent)));
    }

    private void buildInterface() {
        contentWindow = new JScrollPane();
        contentWindow.setBounds(10,50,300,270);

        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.insets = new Insets(0, 10, 0, 10);
        gc.fill = GridBagConstraints.BOTH;
        add(contentWindow, gc);
    }

    private void getFullCatalog() throws SQLException{
        if(listener != null)
            setTable(listener.getCatalog());
    }

    private void fireSearchEvent() throws SQLException {
        if(listener != null)
            setTable(listener.searchByKeyword(searchField.getText()));
    }

    private void setTable(Vector<Vector<Object>> data) {
        String[] titles= {"Make", "Model", "Year", "Price", "Order"};


        DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
        JTable dataTbl = new JTable(tableModel);    //Use tableModel as template for dataTbl
        ClientsTableRenderer renderer = new ClientsTableRenderer(new JCheckBox());

//     Takes multidimen vector of vehicle data and inserts it row by row into model
        for (Vector<Object> datum : data) tableModel.addRow(datum);

        dataTbl.getColumnModel().getColumn(4).setCellRenderer(new ClientsTableButtonRenderer());
        dataTbl.getColumnModel().getColumn(4).setCellEditor(renderer);
        dataTbl.setPreferredScrollableViewportSize(dataTbl.getPreferredSize());
        dataTbl.setShowHorizontalLines(true);
        dataTbl.setShowVerticalLines(false);

        dataTbl.getSelectionModel().addListSelectionListener(e ->
                parent.changePanel(new OrderView(parent, vehicleModels.get(dataTbl.getSelectedRow()))));

        contentWindow.getViewport().add(dataTbl);

    }

//  Setup button to unfilter list
    private void returnToCatButton(){
        JButton showCat = new JButton("Show Catalog");

        showCat.addActionListener(e->{
            try {
                getFullCatalog();
                remove(showCat);
                showCatBtnVisible = false;
                repaint();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(showCat, gc);

        revalidate();
    }

//  Setup list of Vehicle models to pass to order
    public void setVehicleModels(ArrayList<VehicleModel> vehicleModels) {
        this.vehicleModels = vehicleModels;
    }
}

