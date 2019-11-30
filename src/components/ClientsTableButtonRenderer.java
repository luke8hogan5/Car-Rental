package components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *  Author: https://stackoverflow.com/users/340390/bitmap
 *  Source: https://stackoverflow.com/questions/10347983/making-a-jbutton-clickable-inside-a-jtable
 *  Use: This is used to allow order button in CatalogView table
 */

public class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
{
  public ClientsTableButtonRenderer()
  {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
  {
    setForeground(Color.black);
    setBackground(UIManager.getColor("Button.background"));
    setText((value == null) ? "" : value.toString());
    return this;
  }
}
