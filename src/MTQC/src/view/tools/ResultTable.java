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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.testresult.TestResult;

/**
 * Table used to display results obtained from a single test.
 * 
 * @author Javier & Luis
 *
 */
public class ResultTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<TestResult> results;
	private TextField[][] labels;
	private TextField[][] resume;
	private TextField[] cols;

	/**
	 * Constructor for the class.
	 * 
	 * @param results Obtained result for a test.
	 */
	public ResultTable(ArrayList<TestResult> results) {
		setLayout(new BorderLayout());
		this.results = results;
		cols = new TextField[3];
		labels = new TextField[results.size()][3];
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(results.size() + 1, 3));
		centerPanel(center);
		northPanel();
	}

	/**
	 * Creates north panel.
	 */
	private void northPanel() {
		JPanel north = new JPanel();
		north.setBorder(BorderFactory.createTitledBorder("Resume"));
		north.setLayout(new GridLayout(2, 4));
		resume = new TextField[2][4];
		resume[0][0] = new TextField("Live Mutants #");
		resume[0][1] = new TextField("Killed Mutants #");
		resume[0][2] = new TextField("Total Mutants #");
		resume[0][3] = new TextField("Mutant Score %");
		resume[1][0] = new TextField("");
		resume[1][1] = new TextField("");
		resume[1][2] = new TextField("");
		resume[1][3] = new TextField("");

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				north.add(resume[i][j]);
			}
		}
		add(north, BorderLayout.NORTH);

	}

	/**
	 * Creates center panel.
	 * 
	 * @param center JPanel for the center of the view.
	 */
	private void centerPanel(JPanel center) {
		cols[0] = new TextField("Name");
		cols[1] = new TextField("Result");
		cols[2] = new TextField("Killed");
		center.add(cols[0]);
		center.add(cols[1]);
		center.add(cols[2]);
		for (int i = 0; i < results.size(); i++) {
			labels[i][0] = new TextField(results.get(i).getName());
			labels[i][1] = new TextField(results.get(i).toString());
			labels[i][2] = new TextField("");
			center.add(labels[i][0]);
			center.add(labels[i][1]);
			center.add(labels[i][2]);
		}
		add(new JScrollPane(center), BorderLayout.CENTER);
	}

	/**
	 * Updates on the muntant kills.
	 * 
	 * @param kills List of booleans which indicates which mutant dies.
	 */
	public void updateKills(ArrayList<Boolean> kills) {
		int yes = 0;
		int no = 0;
		for (int i = 0; i < kills.size(); i++) {
			if (kills.get(i)) {
				labels[i + 1][2].setText("Yes");
				yes += 1;
			} else {
				labels[i + 1][2].setText("No");
				no += 1;
			}
		}
		resume[1][0].setText(String.valueOf(no));
		resume[1][1].setText(String.valueOf(yes));
		resume[1][2].setText(String.valueOf(kills.size()));
		resume[1][3].setText(String.valueOf((double) Math.round(yes * 1000.0 / kills.size()) / 10.0) + "%");

	}
}
