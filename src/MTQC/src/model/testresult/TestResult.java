/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.testresult;

import java.util.HashMap;

/**
 * This class represents the result obtained from running a test on a particular
 * mutant.
 * 
 * @author Javier & Luis
 *
 */
public abstract class TestResult {
	
	/**
	 * Represents the ocurrences of each result.
	 */
	protected HashMap<String, Long> map;
	
	/**
	 * Name of the mutant the test was runned on.
	 */
	protected String mutantName;
	/**
	 * Identifier for the test.
	 */
	protected int idTest;
	
	/**
	 * Shots.
	 */
	protected int shots;

	/**
	 * Constructor for the class.
	 * 
	 * @param mutantName Name of the mutant.
	 * @param idTest     Identifier for test.
	 */
	public TestResult(String mutantName, int idTest, int shots) {
		this.mutantName = mutantName;
		this.idTest = idTest;
		this.shots = shots;
	}

	/**
	 * Returns the name for this TestResult object.
	 * 
	 * @return String used to identify this particular test result.
	 */
	public abstract String getName();
	
	/**
	 * Saves the result obtained from running this test.
	 * 
	 * @param result String which represents the result map of a test.
	 */
	public void setResult(HashMap<String, Long> map) {
		this.map = map;
	}

	/**
	 * Checks if this mutant lives or dies.
	 * 
	 * @param original   TestResult for the original file.
	 * @param confidence Used to give some estimation on the results.
	 * @return True if mutants dies, False if it lives.
	 */
	public abstract boolean getKill(TestResult original, double confidence);

	/**
	 * Gets the result for this test.
	 * 
	 * @return String or array of strings (in case multiple shots were applied).
	 */
	protected HashMap<String, Long> getResult() {
		return map;
	}
}
