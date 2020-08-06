/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package view;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import model.mutant.Mutant;
import model.mutantoperator.MutantOperator;
import model.testing.Testing;
import model.testresult.TestResult;
import view.mutantgeneratorview.MutantsGenerator;
import view.mutantgeneratorview.Files.NewPathListener;
import view.mutantgeneratorview.MutantsGenerator.NewGenerateListener;
import view.mutantsviewer.MutantsViewer;
import view.testcaserunnerview.TestCaseRunner;
import view.testcaserunnerview.RunOptions.FileComboListener;
import view.testcaserunnerview.TestCaseRunner.RunListener;
import view.testresultsview.TestResultsView;
import view.testresultsview.TestResultsView.ConfidenceListener;

/**
 * Multiple tab panel of the view.
 * 
 * @author Javier & Luis
 *
 */
public class TabbedPane extends JTabbedPane {
	/**
	 * Unique identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Tab used to generate mutants.
	 */
	private MutantsGenerator mutantsgenerator;

	/**
	 * Tab used to show dif between original files and mutants.
	 */
	private MutantsViewer mutantsViewer;

	/**
	 * Tab used to run test on selected mutants.
	 */
	private TestCaseRunner testCaseRunner;

	/**
	 * Tab used to check results on runned tests.
	 */
	private TestResultsView testResults;

	/**
	 * Constructor for the class.
	 * 
	 * @param listener           Listener for path field.
	 * @param listenGenerate     Listener for generate button.
	 * @param listenerComboFile  Listener for file selection.
	 * @param listenerRun        Listener for run button on TestCase.
	 * @param listenerConfidence Listener for confidence parameter.
	 */
	public TabbedPane(NewPathListener listener, NewGenerateListener listenGenerate, FileComboListener fileComboListener,
			RunListener listenerRun, ConfidenceListener listenerConfidence) {
		mutantsgenerator = new MutantsGenerator(listener, listenGenerate);
		addTab("Mutants Generator", mutantsgenerator);

		mutantsViewer = new MutantsViewer();
		addTab("Mutants Viewer", mutantsViewer);

		testCaseRunner = new TestCaseRunner(fileComboListener, listenerRun);
		addTab("TestCase Runner", testCaseRunner);

		testResults = new TestResultsView(listenerConfidence);
		addTab("Test Results", testResults);

	}

	/**
	 * Updates with files on new path.
	 * 
	 * @param files Files on the new path determined by user.
	 */
	public void updatePath(ArrayList<String> files) {
		mutantsgenerator.updatePath(files);
		testCaseRunner.refreshFileCombo(files);
	}

	/**
	 * Updtates the view with the mutant operators for the selected language.
	 * 
	 * @param mutantOperatorList List of new operators.
	 * @param qiskit             To check if qiskit language is selected.
	 */
	public void updateOperators(MutantOperator[] mutantOperatorList) {
		mutantsgenerator.updateOperators(mutantOperatorList);
	}

	/**
	 * Updates the view with the generated mutants.
	 * 
	 * @param mutantList List of the new mutants generated.
	 */
	public void updateMutants(ArrayList<Mutant> mutantList) {
		mutantsViewer.updateMutants(mutantList);
	}

	/**
	 * Updates the view with the methods declared inside a file.
	 * 
	 * @param fileMethods Methods inside the file.
	 * @param mutants     Mutants obtained by applying operators on the file.
	 * @param fileName    Name of the file
	 */
	public void updateFileMethods(ArrayList<String> fileMethods, ArrayList<Mutant> mutants) {
		testCaseRunner.updateFileMethods(fileMethods);
		testCaseRunner.updateMutants(mutants);
	}

	/**
	 * Sets the different type of test available.
	 * 
	 * @param tests List with the type of test.
	 */
	public void setTests(Testing[] tests) {
		testCaseRunner.setTests(tests);
	}

	/**
	 * Notifies a message to the MutantGenerator tab.
	 * 
	 * @param msg Message to be notified.
	 */
	public void notifyMutantsGenerator(String msg) {
		mutantsgenerator.notify(msg);
	}

	/**
	 * Notifies a message to the TestCaseRunner tab.
	 * 
	 * @param msg Message to be notified.
	 */
	public void notifyTestCaseRunner(String msg) {
		testCaseRunner.notify(msg);
	}

	/**
	 * Notifies the results to the Results tab.
	 * 
	 * @param results List of test results obtained by the program.
	 */
	public void notifyResults(ArrayList<ArrayList<TestResult>> results) {
		testResults.update(results);
	}

	/**
	 * Updates the kills on the Result tab.
	 * 
	 * @param kills
	 */
	public void updateKills(ArrayList<ArrayList<Boolean>> kills) {
		testResults.updateKills(kills);
	}

	/**
	 * Notify when run process starts and finishes. Lock and unlock run button.
	 * 
	 * @param started true if starts, false if finishes.
	 */
	public void startedRun(boolean started) {
		testCaseRunner.startedRun(started);
	}

	public void updateInputText(String inputExample) {
		testCaseRunner.updateLanguage(inputExample);
	}

}
