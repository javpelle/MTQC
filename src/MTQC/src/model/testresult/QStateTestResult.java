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

/**
 * Concrete TestResult which represents a type of test which checks the concrete
 * values of the quantum system.
 * 
 * @author Javier & Luis
 *
 */
public class QStateTestResult extends TestResult {
	private String result;

	/**
	 * Constructor for the class.
	 * 
	 * @param mutantName Name of the mutant.
	 * @param idTest     Identifier for test.
	 */
	public QStateTestResult(String mutantName, int idTest) {
		super(mutantName, idTest);

	}

	@Override
	public String getName() {
		return mutantName + "_" + Integer.toString(idTest);
	}

	@Override
	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void make() {
	}

	/**
	 * Overrides Object toString method.
	 */
	public String toString() {
		return result;
	}

	@Override
	public boolean getKill(TestResult original, double confidence) {
		return !result.equals((String) original.getResult());
	}

	@Override
	protected Object getResult() {
		return result;
	}
}
