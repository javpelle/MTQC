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

import model.testresult.TestResult;

/**
 * This class represents a type of test.
 * 
 * @author Javier & Luis
 *
 */
public abstract class Testing {
	/**
	 * This method returns and identifier for a test.
	 * 
	 * @return Test identifier.
	 */
	public abstract int getIDTest();

	/**
	 * This method returns a name for a test.
	 * 
	 * @return Name of the type of test.
	 */
	public abstract String getNameTest();

	/**
	 * Sets the number of iterations the program needs to run each test.
	 * 
	 * @param shots Number of iterations wanted.
	 */
	public abstract void setShots(int shots);

	/**
	 * Returns the number of iterations the program needs to run each test.
	 * 
	 * @return Number of iterations.
	 */
	public abstract int getShots();

	/**
	 * Overrides the Object method toString.
	 */
	public String toString() {
		return getNameTest();
	}

	/**
	 * Generates an adequate TestResult for this type of test.
	 * 
	 * @param mutantName Name of the mutant.
	 * @param idTest     Identifier for the test.
	 * @return A new TestResult objet.
	 */
	public abstract TestResult newTestResult(String mutantName, int idTest);
	
	/**
	 * Return execute Qiskit method with default backend.
	 * 
	 * @param shots 
	 * @return
	 */
	public abstract String getQiskitSimulator(int shots);
	
	/**
	 * Return Qiskit counts from execute.
	 * @return
	 */
	public abstract String getQiskitCounts();
	
	/**
	 * Returns testing type.
	 * @return ETestingType
	 */
	public abstract ETestingType getTestingType();

}
