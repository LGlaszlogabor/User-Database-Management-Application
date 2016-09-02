package common;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorCellRenderer extends JLabel implements TableCellRenderer {
	private static final long serialVersionUID = 1L;
	public ColorCellRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object color,boolean isSelected, boolean hasFocus,
																			int row, int column) {
		Color newColor = (Color)color;
		setBackground(newColor);
		return this;
	}
}
