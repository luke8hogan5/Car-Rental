package components;

import java.awt.Color;
import java.awt.Component;
//import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * TODO: add reference and give improvement
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
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        fireEditingStopped();
      }
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
  public Object getCellEditorValue()
  {
    if (clicked)
    {
      JOptionPane.showMessageDialog(button, "Column with Value: "+table.getValueAt(row, 4) + " -  Clicked!");
    }
    clicked = false;
    return new String(label);
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
