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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.mutantoperator.MutantOperator;

public class JTableCheckInfo<T> extends JTableCheck<T> {

	private static final long serialVersionUID = 1L;

	public JTableCheckInfo(Object[] columnNames) {
		super(columnNames);
	}

	public void onDoubleClick() {
		MutantOperator m =(MutantOperator) model.getValueAt(getSelectedRow(), 1); 
		String msg = m.getDescription();
		JFrame info = new JFrame();
		JOptionPane.showMessageDialog(info, msg);
	}

}
