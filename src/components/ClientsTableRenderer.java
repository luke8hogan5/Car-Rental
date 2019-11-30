package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *  Author: https://stackoverflow.com/users/340390/bitmap
 *  Source: https://stackoverflow.com/questions/10347983/making-a-jbutton-clickable-inside-a-jtable
 *  Use: This is used to allow order button in CatalogView table
 */

public class ClientsTableRenderer extends DefaultCellEditor
{
  private JButton button;
  private String label;
  private boolean clicked;
  private int row, col;
  private JTable table;

  public ClientsTableRenderer(JCheckBox checkBox)
  {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(e ->
    {
        fireEditingStopped();
    });
  }
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
  {
    this.table = table;
    this.row = row;
    this.col = column;

    button.setForeground(Color.black);
    button.setBackground(UIManager.getColor("Button.background"));
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    clicked = true;
    return button;
  }
  public Vector getCellEditorValue()
  {
    if (clicked){
      DefaultTableModel model = (DefaultTableModel) table.getModel();

      return (Vector) model.getDataVector().elementAt(row);
    }
    clicked = false;

    return null;
  }

  public boolean stopCellEditing()
  {
    clicked = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped()
  {
    super.fireEditingStopped();
  }
}
