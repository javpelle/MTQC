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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.Controller;
import model.Observable;
import model.Observer;
import model.mutant.Mutant;
import model.mutantoperator.MutantOperator;
import model.testing.Testing;
import model.testresult.TestResult;
import view.MenuBar.LanguageListener;
import view.MenuBar.ResetListener;
import view.mutantgeneratorview.Files.NewPathListener;
import view.mutantgeneratorview.MutantsGenerator.NewGenerateListener;
import view.testcaserunnerview.RunOptions.FileComboListener;
import view.testcaserunnerview.TestCaseRunner.RunListener;
import view.testresultsview.TestResultsView.ConfidenceListener;

/**
 * View for the entire program.
 * 
 * @author Javier & Luis
 *
 */
public class UI extends JFrame implements Observer {

	/**
	 * Unique identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Menu bar for the view.
	 */
	private MenuBar menuBar;

	/**
	 * Multiple tab object of the view.
	 */
	private TabbedPane tabbedPane;

	/**
	 * Constructor of view
	 * 
	 * @param c a Controller
	 * @param o an Observable for add this view as observer.
	 * @param languages 
	 */
	public UI(Controller c, Observable<Observer> o) {
		super("Mutation Testing for Quantum Computing");
		// Add this view as observer
		o.addObserver(this);

		setIconImage(new ImageIcon("icon.jpg").getImage());

		// Size and Layout of view
		setSize(new Dimension(1280, 720));
		setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				c.removeMutants();
			}
		});

		// Add menu bar
		menuBar = new MenuBar(new LanguageListener() {

			@Override
			public void languageChosen(int language) {
				c.updateLanguage(language);
			}

		}, new ResetListener() {

			@Override
			public void reset() {
				c.reset();
			}

		});
		setJMenuBar(menuBar);

		// Add tabbed pane
		tabbedPane = new TabbedPane(new NewPathListener() {

			@Override
			public void updatePath(String path) {
				c.updatePath(path);
			}

		}, new NewGenerateListener() {

			@Override
			public void generate(ArrayList<String> files, ArrayList<MutantOperator> operators) {
				c.generate(files, operators);

			}

		}, new FileComboListener() {

			@Override
			public void refreshPath() {
				c.refreshPath();
			}

			@Override
			public void refreshMethods(String fileName) {
				c.getFileMethods(fileName);

			}

		}, new RunListener() {

			@Override
			public void runTests(ArrayList<Mutant> selectedMutants, String fileName, String methodName, Testing testType,
					int shots, ArrayList<String> testFileName, double timeLimit) {
				tabbedPane.startedRun(true);
				c.runTests(selectedMutants, fileName, methodName, testType, shots, testFileName, timeLimit);
				tabbedPane.startedRun(false);
			}

		}, new ConfidenceListener() {

			@Override
			public void updateConfidence(double confidence) {
				c.updateConfidence(confidence);
			}

		});
		add(tabbedPane, BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void onError(String msg) {
		JFrame error = new JFrame();
		JOptionPane.showMessageDialog(error, msg);
	}

	@Override
	public void updateMutantOperators(MutantOperator[] mutantOperatorList, String example) {
		tabbedPane.updateOperators(mutantOperatorList, example);
	}

	@Override
	public void updatePath(ArrayList<String> files) {
		tabbedPane.updatePath(files);
	}

	@Override
	public void updateMutants(ArrayList<Mutant> mutantList) {
		tabbedPane.updateMutants(mutantList);
	}

	@Override
	public void updateFileMethods(ArrayList<String> fileMethods, ArrayList<Mutant> mutants) {
		tabbedPane.updateFileMethods(fileMethods, mutants);
	}

	@Override
	public void setTests(Testing[] tests) {
		tabbedPane.setTests(tests);
	}

	@Override
	public void notifyMutantsGenerator(String msg) {
		tabbedPane.notifyMutantsGenerator(msg);
	}

	@Override
	public void notifyTestCaseRunner(String msg) {
		tabbedPane.notifyTestCaseRunner(msg);
	}

	@Override
	public void notifyResults(ArrayList<ArrayList<TestResult>> results) {
		tabbedPane.notifyResults(results);
	}

	@Override
	public void updateKills(ArrayList<ArrayList<Boolean>> kills) {
		tabbedPane.updateKills(kills);
	}

}
