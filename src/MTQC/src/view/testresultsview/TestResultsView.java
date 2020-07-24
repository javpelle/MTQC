/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view.testresultsview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.testresult.TestResult;

/**
 * Tab used to shows results.
 * 
 * @author Javier & Luis
 *
 */
public class TestResultsView extends JPanel {

	private static final long serialVersionUID = 1L;

	private TabbedResultTable table;

	private JSpinner confidence;

	private JButton setConfidence;

	/**
	 * Constructor for the class.
	 * 
	 * @param listener Listener for the confidence field.
	 */
	public TestResultsView(ConfidenceListener listener) {
		setLayout(new BorderLayout());
		northPanel(listener);
		centerPanel();
	}

	/**
	 * Creates north panel.
	 * 
	 * @param listener Listener for the confidence field.
	 */
	private void northPanel(ConfidenceListener listener) {
		JPanel north = new JPanel();
		north.setBorder(BorderFactory.createTitledBorder("Confidence Interval (%)"));
		confidence = new JSpinner(new SpinnerNumberModel(1.0, 0, 100.0, 0.1));
		north.add(confidence);
		setConfidence = new JButton("Set confindence (%)");

		setConfidence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.updateConfidence((double) confidence.getValue());
			}
		});

		north.add(setConfidence);
		add(north, BorderLayout.NORTH);
	}

	/**
	 * Creates center panel.
	 */
	private void centerPanel() {
		table = new TabbedResultTable();
		add(table, BorderLayout.CENTER);
	}

	/**
	 * Updates results
	 * 
	 * @param results List of test results.
	 */
	public void update(ArrayList<ArrayList<TestResult>> results) {
		table.update(results);
	}

	/**
	 * Updates which mutants dies if confidence parameter changes.
	 * 
	 * @param kills List of booleans that indicates kills on mutants.
	 */
	public void updateKills(ArrayList<ArrayList<Boolean>> kills) {
		table.updateKills(kills);

	}

	/**
	 * Interface listener for confidence parameter.
	 * 
	 * @author Javier & Luis
	 *
	 */
	public interface ConfidenceListener {
		public void updateConfidence(double confidence);
	}
}
