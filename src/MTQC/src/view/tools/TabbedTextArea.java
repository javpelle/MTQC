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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * Multiple panel object used to allow the user to write desired tests
 * 
 * @author Javier & Luis
 *
 */
public class TabbedTextArea extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	private ArrayList<JTextArea> windows;

	/**
	 * Constructor for the class.
	 */
	public TabbedTextArea() {
		windows = new ArrayList<JTextArea>();
		firstWindow();
	}

	/**
	 * Adds a new tab.
	 */
	public void newWindow() {
		JPanel aux = new JPanel(new BorderLayout());
		JTextArea auxWindow = new JTextArea(windows.get(getSelectedIndex()).getText());
		windows.add(auxWindow);
		aux.add(new JScrollPane(auxWindow), BorderLayout.CENTER);
		addTab(String.valueOf(windows.size()), aux);
		setSelectedIndex(windows.size() - 1);
	}

	/**
	 * Creates the first tab.
	 */
	private void firstWindow() {
		JPanel aux = new JPanel(new BorderLayout());
		JTextArea auxWindow = new JTextArea();
		windows.add(auxWindow);
		aux.add(new JScrollPane(auxWindow), BorderLayout.CENTER);
		addTab(String.valueOf(windows.size()), aux);
	}

	/**
	 * Removes the selected tab.
	 */
	public void removeWindow() {
		if (windows.size() > 1) {
			int index = getSelectedIndex();
			remove(index);
			windows.remove(index);
			updateIndex();
		}
	}

	/**
	 * Updates the index of each tab.
	 */
	private void updateIndex() {
		for (int i = 0; i < windows.size(); i++) {
			this.setTitleAt(i, String.valueOf(i + 1));
		}
	}

	/**
	 * Gets the test for the tab collection.
	 * 
	 * @return A list of tests.
	 */
	public ArrayList<String> getTests() {
		ArrayList<String> tests = new ArrayList<String>();
		for (JTextArea w : windows) {
			tests.add(w.getText());
		}
		return tests;
	}

	/**
	 * Updates example test for the tab on language change.
	 * 
	 * @param language Boolean used to know if Qiskit language is selected.
	 */
	public void updateLanguage(String example) {
		for (JTextArea w : windows) {
			w.setText(example);
		}
	}

}
