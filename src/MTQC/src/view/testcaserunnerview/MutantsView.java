/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.testcaserunnerview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.mutant.Mutant;
import view.tools.JTableCheck;

/**
 * Shows generated mutants for a file.
 * 
 * @author Javier & Luis
 *
 */
public class MutantsView extends JPanel {

	private static final long serialVersionUID = 1L;

	private final static Object[] column = { "", "Mutant" };

	private JPanel center;

	private JTableCheck<Mutant> table;

	private JButton all;

	private JButton none;

	/**
	 * Empty constructor.
	 */
	public MutantsView() {
		setLayout(new BorderLayout());

		createCenterPanel();

	}

	/**
	 * Creates center panel.
	 */
	private void createCenterPanel() {
		center = new JPanel();
		center.setLayout(new BorderLayout());
		table = new JTableCheck<Mutant>(column);
		all = new JButton("All");
		none = new JButton("None");
		JPanel centerSouth = new JPanel();
		centerSouth.add(all);
		centerSouth.add(none);
		center.add(new JScrollPane(table), BorderLayout.CENTER);
		center.add(centerSouth, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);

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
	 * Gets the selected mutants.
	 * 
	 * @return A list with the selected mutants.
	 */
	public ArrayList<Mutant> getSelectedFiles() {
		return table.getTrueRows();
	}

	/**
	 * Updates the view with given mutants.
	 * 
	 * @param mutants List of mutants needed to be show.
	 * @param file    Name of the selected file.
	 */
	public void updateMutants(ArrayList<Mutant> mutants) {
		table.clear();
		for (Mutant m: mutants) {
			table.addRow(new Object[] { false, m });
		}
	}

}
