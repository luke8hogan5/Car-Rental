package views;
import components.ClientsTableButtonRenderer;
import components.ClientsTableRenderer;
import controllers.CatalogController;
import interfaces.CatalogListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

public class CatalogView extends JPanel{
    private MasterView parent;
    private JTextField searchField;
    private CatalogListener listener;
    private JScrollPane contentWindow;
    private Boolean isCatalogFiltered = false;
    private Button showCat;
    private GridBagConstraints gc;
	
    public CatalogView(MasterView parent) {
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

    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu accNav = new JMenu("Account");
        accNav.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                parent.changePanel(new ProfileView(parent));
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }
            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        Button searchBtn = new Button("Search");
        searchBtn.addActionListener(e -> {
            try {
                fireSearchEvent();
                returnToCatButton();
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
    }

    private void buildInterface() {
        contentWindow = new JScrollPane();
        contentWindow.setBounds(10,50,300,270);

        showCat = new Button("Show Catalog");
        showCat.addActionListener(e -> {
            try {
                getFullCatalog();
                returnToCatButton();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.insets = new Insets(0, 10, 0, 10);
        gc.fill = GridBagConstraints.HORIZONTAL;
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
        for (Vector<Object> objects : data) {
            for (Object object : objects) System.out.printf("%s ", object);
            System.out.print("\n");
        }

        String[] titles= {"Make", "Model", "Year", "Price", "Order"};


        DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
        JTable dataTbl = new JTable(tableModel);
        ClientsTableRenderer renderer = new ClientsTableRenderer(new JCheckBox());

        for (Vector<Object> datum : data) tableModel.addRow(datum);

        dataTbl.getColumnModel().getColumn(4).setCellRenderer(new ClientsTableButtonRenderer());
        dataTbl.getColumnModel().getColumn(4).setCellEditor(renderer);
        dataTbl.setPreferredScrollableViewportSize(dataTbl.getPreferredSize());
        dataTbl.setShowHorizontalLines(true);
        dataTbl.setShowVerticalLines(false);

        dataTbl.getSelectionModel().addListSelectionListener(e -> {
            parent.changePanel(new OrderView(parent, tableModel.getDataVector().elementAt(dataTbl.getSelectedRow())));
        });

        contentWindow.getViewport().add(dataTbl);

    }

    public void returnToCatButton(){
        if(!isCatalogFiltered) {
            isCatalogFiltered = true;

            gc.anchor = GridBagConstraints.NORTH;
            gc.gridy = 2;
            gc.weightx = 1;
            gc.weighty = 100;
            gc.fill = GridBagConstraints.HORIZONTAL;
            add(showCat, gc);
        }else{
            isCatalogFiltered = false;
            remove(showCat);
        }
    }
}

