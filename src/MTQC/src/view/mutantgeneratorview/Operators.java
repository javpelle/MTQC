/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.mutantgeneratorview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.mutantoperator.MutantOperator;
import view.tools.JTableCheckInfo;

/**
 * Panel which shows all mutants availables for the selected language.
 * 
 * @author Javier & Luis
 *
 */
public class Operators extends JPanel {

	private static final long serialVersionUID = 1L;

	private final static Object[] column = { "", "Operators" };

	private JTableCheckInfo<MutantOperator> table;

	private JButton all;

	private JButton none;

	/**
	 * Construcotr for the class.
	 */
	public Operators() {
		setLayout(new BorderLayout());

		createCenterPanel();

		createSouthPanel();

	}

	/**
	 * Creates the center panel of the view.
	 */
	private void createCenterPanel() {
		table = new JTableCheckInfo<MutantOperator>(column);
		add(new JScrollPane(table), BorderLayout.CENTER);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					// Double-click detected
					table.onDoubleClick();
				}
			}
		});
	}

	/**
	 * Creates the south panel of the view.
	 */
	private void createSouthPanel() {
		all = new JButton("All");
		none = new JButton("None");
		JPanel south = new JPanel();
		south.add(all);
		south.add(none);
		add(south, BorderLayout.SOUTH);

		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setAllTrue();
			}
		});

		none.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setAllFalse();
			}
		});
	}

	/**
	 * Updates the operators to be show.
	 * 
	 * @param mutantOperatorList List of mutant operators needed to be show.
	 */
	public void updateOperators(MutantOperator[] mutantOperatorList) {
		table.clear();
		for (int i = 0; i < mutantOperatorList.length; ++i) {
			table.addRow(new Object[] { false, mutantOperatorList[i] });
		}
	}

	/**
	 * Gets the selected operators by the user.
	 * 
	 * @return A list with the mutant operators selected by the user.
	 */
	public ArrayList<MutantOperator> getSelectedOperators() {
		return table.getTrueRows();
	}
}
