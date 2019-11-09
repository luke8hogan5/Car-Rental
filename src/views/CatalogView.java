package views;
import interfaces.CatalogListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CatalogView extends MasterView{
    private JTextField searchField;
    private CatalogListener listener;
    private JScrollPane contentWindow;
	
    public CatalogView() {
        super();
        contentWindow = new JScrollPane();
        contentWindow.setBounds(10,50,300,270);

        Button showCat = new Button("Show Catalog");
        showCat.addActionListener(e->{
            try{
                getFullCatalog();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(showCat,gc);

        gc.gridy = 2;
        add(contentWindow);

        JMenuBar menuBar = new JMenuBar();
        JMenu homeNav = new JMenu("Home");
        homeNav.setMnemonic(KeyEvent.VK_F);
        JMenu accNav = new JMenu("Account");
        accNav.setMnemonic(KeyEvent.VK_F);
        JMenu orderNav = new JMenu("Order Form");
        orderNav.setMnemonic(KeyEvent.VK_F);
        JMenu contactNav = new JMenu("Contact");
        contactNav.setMnemonic(KeyEvent.VK_F);
        Button searchBtn = new Button("Search");
        searchBtn.addActionListener(e -> {
            try {
                fireSearchEvent();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        searchField = new JTextField(20);
        searchField.setText("Search Keyword");

        menuBar.add(homeNav);
        menuBar.add(accNav);
        menuBar.add(orderNav);
        menuBar.add(contactNav);
        menuBar.add(new JSeparator(), CENTER_ALIGNMENT);
        menuBar.add(searchField);
        menuBar.add(searchBtn);

        setJMenuBar(menuBar);
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
        for(int i=0; i<data.size(); i++){
            for(int j=0; j<data.get(i).size(); j++)
                System.out.printf("%s ",data.get(i).get(j));
            System.out.print("\n");
        }

        String[] titles= {"Make", "Model", "Year", "Price"};


        DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
        JTable dataTbl = new JTable(tableModel);

        for(int x=0; x<data.size(); x++)
            tableModel.addRow(data.get(x));

        contentWindow.getViewport().add(dataTbl);

    }

    public void setListener(CatalogListener listener) {
        this.listener = listener;
    }
}

