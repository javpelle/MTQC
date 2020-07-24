/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Model;
import model.mutant.Mutant;
import model.mutantoperator.MutantOperator;
import model.testing.Testing;

/**
 * Controller for the MVC.
 * 
 * @author Javier & Luis
 *
 */
public class Controller {
	/**
	 * Model from MVC
	 */
	private Model model;

	/**
	 * Token used to split test from file
	 */
	private static final String testTokenSeparator = "***";

	/**
	 * Class constructor
	 * 
	 * @param model Model for MVC
	 */
	public Controller(Model model) {
		this.model = model;
	}

	/**
	 * Starts model
	 */
	public void start() {
		model.start();
	}

	/**
	 * Resets model
	 */
	public void reset() {
		model.reset();
	}

	/**
	 * Updates user path
	 * 
	 * @param path New path to be used
	 */
	public void updatePath(String path) {
		model.updatePath(path);
	}

	/**
	 * 
	 * @param qiskit Boolean used to check which language is selected
	 */
	public void updateLanguage(int language) {
		model.updateMutantOperators(language);
	}

	/**
	 * Generates all posible mutants
	 * 
	 * @param files     Files to be mutated
	 * @param operators Operators to be used
	 */
	public void generate(ArrayList<String> files, ArrayList<MutantOperator> operators) {
		model.generate(files, operators);

	}

	/**
	 * Deletes all mutants
	 */
	public void removeMutants() {
		model.removeMutants();
	}

	/**
	 * Refreshes user path
	 */
	public void refreshPath() {
		model.refreshPath();
	}

	/**
	 * Gets the name of all the methods in a file
	 * 
	 * @param fileName File it gets the methods from
	 */
	public void getFileMethods(String fileName) {
		model.getFileMethods(fileName);

	}

	/**
	 * Updates confidenc to be used for results
	 * 
	 * @param confidence Percetange of confindence to check if a mutant dies or
	 *                   lives
	 */
	public void updateConfidence(double confidence) {
		model.updateConfidence(confidence);
	}

	/**
	 * Runs the tests selected by user
	 * 
	 * @param selectedMutants Mutants selected to run test on
	 * @param fileName        Original file name
	 * @param methodName      Method name to be tested
	 * @param testType        Type of test
	 * @param shots           Number of times it will run each test
	 * @param testList        Test suite
	 * @param timeLimit       Max time each test can run
	 */
	public void runTests(ArrayList<Mutant> selectedMutants, String fileName, String methodName, Testing testType,
			int shots, ArrayList<String> testList, double timeLimit) {
		ArrayList<String> testSuite;
		String file = testList.get(testList.size() - 1);
		if (!file.equals("")) {
			testSuite = setTestFromFile(file);
		} else {
			testList.remove(testList.size() - 1);
			testSuite = testList;
		}
		testType.setShots(shots);
		model.run(selectedMutants, testSuite, testType, fileName, methodName, timeLimit);
	}

	/**
	 * Splits the tests from a file
	 * 
	 * @param testFileName Name of the file which contains the tests
	 * @return Array which contains test in string format
	 */
	private ArrayList<String> setTestFromFile(String testFileName) {
		File file = new File(testFileName);
		ArrayList<String> testSuite = new ArrayList<String>();
		BufferedReader reader = null;
		String testString = "";

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {

				if (line.equals(testTokenSeparator)) {
					testSuite.add(testString);
					testString = "";

				} else {
					testString = testString + line + System.lineSeparator();
				}

				line = reader.readLine();
			}

		} catch (IOException e) {
			model.notifyError(e);

		} finally {
			try {
				reader.close();

			} catch (IOException e) {
				model.notifyError(e);
			}
		}
		return testSuite;
	}

}
