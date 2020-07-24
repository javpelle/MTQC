/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.tools;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Extension for JTable to implement checklist type.
 * 
 * @author Javier & Luis
 *
 * @param <T> Generic type class.
 */
public class JTableCheck<T> extends JTable {

	private static final long serialVersionUID = 1L;

	protected DefaultTableModel model;

	/**
	 * Construcor for the class.
	 * 
	 * @param columnNames Name of the columns.
	 */
	public JTableCheck(Object[] columnNames) {
		super(new DefaultTableModel(columnNames, 0));
		model = (DefaultTableModel) getModel();
		getTableHeader().setReorderingAllowed(false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * Gets the type of the column.
	 */
	public Class getColumnClass(int column) {
		switch (column) {
		case 0:
			return Boolean.class;
		default:
			return String.class;
		}
	}

	/**
	 * Checks if a cell is editable.
	 */
	public boolean isCellEditable(int row, int column) {
		return column == 0;
	}

	/**
	 * Add a new row.
	 * 
	 * @param o List of objects for the new row.
	 */
	public void addRow(Object[] o) {
		model.addRow(o);
	}

	/**
	 * Clears the table.
	 */
	public void clear() {
		if (model.getRowCount() > 0) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
	}

	/**
	 * Selects all items.
	 */
	public void setAllTrue() {
		if (model.getRowCount() > 0) {
			for (int i = 0; i < model.getRowCount(); ++i) {
				model.setValueAt(true, i, 0);
			}
		}
	}

	/**
	 * Unchecks all items.
	 */
	public void setAllFalse() {
		if (model.getRowCount() > 0) {
			for (int i = 0; i < model.getRowCount(); ++i) {
				model.setValueAt(false, i, 0);
			}
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Gets selected rows.
	 * 
	 * @return A list with selected rows.
	 */
	public ArrayList<T> getTrueRows() {
		ArrayList<T> selectedRows = new ArrayList<T>();
		for (int i = 0; i < model.getRowCount(); i++) {
			if ((boolean) model.getValueAt(i, 0)) {
				selectedRows.add((T) getValueAt(i, 1));
			}
		}
		return selectedRows;
	}
}
