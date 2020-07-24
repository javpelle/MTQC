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

import java.util.ArrayList;

import javax.swing.JTabbedPane;
import model.testresult.TestResult;
import view.tools.ResultTable;

/**
 * List of tabs where each tab shows the results obtained for a certain test.
 * 
 * @author Javier & Luis.
 *
 */
public class TabbedResultTable extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	private ArrayList<ResultTable> tabs;

	/**
	 * Empty constructor.
	 */
	public TabbedResultTable() {
		tabs = new ArrayList<ResultTable>();

	}

	/**
	 * Updates the results.
	 * 
	 * @param results List of results.
	 */
	public void update(ArrayList<ArrayList<TestResult>> results) {
		deleteAll();
		for (ArrayList<TestResult> t : results) {
			ResultTable aux = new ResultTable(t);
			tabs.add(aux);
			addTab("Test " + String.valueOf(tabs.size()), aux);
		}
	}

	/**
	 * Clears all tabs.
	 */
	private void deleteAll() {
		tabs.clear();
		this.removeAll();
	}

	/**
	 * Updates the kills on mutants.
	 * 
	 * @param kills List of booleans which indicates which mutants die.
	 */
	public void updateKills(ArrayList<ArrayList<Boolean>> kills) {

		for (int i = 0; i < kills.size(); i++) {
			tabs.get(i).updateKills(kills.get(i));
		}
	}

}
