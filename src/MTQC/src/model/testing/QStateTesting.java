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

import model.testresult.QStateTestResult;
import model.testresult.TestResult;

/**
 * Concrete Test which represents a test which checks the internal state of a
 * quantum system. This type of test is only useful for simulation.
 * 
 * @author Javier & Luis
 *
 */
public class QStateTesting extends Testing {

	@Override
	public int getIDTest() {
		return 1;
	}

	@Override
	public String getNameTest() {
		return "QStateTest (only simulator)";
	}

	@Override
	public void setShots(int shots) {

	}

	@Override
	public int getShots() {
		return 1;
	}

	@Override
	public TestResult newTestResult(String mutantName, int idTest) {
		return new QStateTestResult(mutantName, idTest);
	}

	@Override
	public String getQiskitSimulator(int shots) {
		return "ex = execute(qc, backend = Aer.get_backend('statevector_simulator'))";
	}

	@Override
	public String getQiskitCounts() {
		
		return "return pow(abs(ex.result().get_statevector()), 2)";
	}

}
