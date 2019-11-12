package views;

import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import controllers.OrderControllerAdm;
import interfaces.OrderListenerAdm;
import models.OrderModelAdm;

public class OrderViewAdm extends JPanel{
	private MasterView parent;

	private JScrollPane scrollPane;

	private OrderListenerAdm orderAdmListener;
	
	OrderViewAdm(MasterView parent) throws SQLException {
		super();
		this.parent = parent;
		orderAdmListener = new OrderControllerAdm(this);

		setupMenu();

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 100;
		gc.insets = new Insets(0, 10, 0, 10);
		gc.fill = GridBagConstraints.HORIZONTAL;
		scrollPane = new JScrollPane();
		add(scrollPane, gc);

		fireOrderAdmEvent();
	}

	private void setupMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu usersNav = new JMenu("Users");
		usersNav.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				parent.changePanel(new UsersViewAdm(parent));
			}

			@Override
			public void menuDeselected(MenuEvent e) {

			}
			@Override
			public void menuCanceled(MenuEvent e) {

			}
		});
		JMenu stockNav = new JMenu("Stock");
		stockNav.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				parent.changePanel(new VehicleViewAdm(parent));
			}

			@Override
			public void menuDeselected(MenuEvent e) {

			}
			@Override
			public void menuCanceled(MenuEvent e) {

			}
		});

		menuBar.add(usersNav);
		menuBar.add(stockNav);

		add(menuBar);
	}

	private void fireOrderAdmEvent() throws SQLException {
		if (orderAdmListener != null) {
			setTable(orderAdmListener.orderAdmPerformed());
		}
	}

	private void setTable(Vector<Vector<Object>> data) {
		String[] titles= {"Date Created","Due Date","PaymentCleared"};


		DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
		JTable dataTbl = new JTable(tableModel);

		for (Vector<Object> datum : data) tableModel.addRow(datum);

		dataTbl.setPreferredScrollableViewportSize(dataTbl.getPreferredSize());
		dataTbl.setShowHorizontalLines(true);
		dataTbl.setShowVerticalLines(false);

		scrollPane.getViewport().add(dataTbl);

	}
}

