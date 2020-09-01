/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.testing;

import model.testresult.ProbabilistTestResult;
import model.testresult.TestResult;

/**
 * Concrete Test which represents a "Montecarlo" type of test.
 * 
 * @author Javier & Luis.
 *
 */
public class ProbabilisticTesting extends Testing {

	private int shots;

	/**
	 * Constructor for the class.
	 */
	public ProbabilisticTesting() {
		shots = 0;
	}

	@Override
	public int getIDTest() {
		return 2;
	}

	@Override
	public String getNameTest() {
		return "ProbabilisticTest";
	}

	@Override
	public void setShots(int shots) {
		this.shots = shots;
	}

	@Override
	public int getShots() {
		return shots;
	}

	@Override
	public TestResult newTestResult(String mutantName, int idTest) {
		return new ProbabilistTestResult(mutantName, idTest, shots);
	}

	@Override
	public String getQiskitSimulator(int shots) {
		return "ex = execute(qc, backend = Aer.get_backend('qasm_simulator'), shots = " + Integer.toString(shots) +")";
	}

	@Override
	public String getQiskitCounts() {
		return "return ex.result().get_counts()";
	}

	@Override
	public ETestingType getTestingType() {
		return ETestingType.PROBABILISTICTESTING;
	}

}
