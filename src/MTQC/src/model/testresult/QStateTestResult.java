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
 * Concrete TestResult which represents a type of test which checks the concrete
 * values of the quantum system.
 * 
 * @author Javier & Luis
 *
 */
public class QStateTestResult extends TestResult {

	/**
	 * Constructor for the class.
	 * 
	 * @param mutantName Name of the mutant.
	 * @param idTest     Identifier for test.
	 */
	public QStateTestResult(String mutantName, int idTest, int shots) {
		super(mutantName, idTest, shots);
	}

	@Override
	public String getName() {
		return mutantName + "_" + Integer.toString(idTest);
	}

	/**
	 * Overrides Object toString method.
	 */
	public String toString() {
		String ret = "";
		for (HashMap.Entry<String, Long> entry : map.entrySet()) {
			if (entry.getValue().longValue() == -1l || entry.getValue().longValue() == -2l) {
				errorDetected = true;
				return entry.getValue().longValue() == -1l? "Timeout" : "Execution error";
			}
			if (entry.getValue() != 0) {
				ret = " |" + entry.getKey() + "> = " + (double) entry.getValue() + "%," + ret;
			}
		}
		return "[" + ret.substring(0, ret.length() - 1) + " ]";
	}
	
//	private String toBase2(String integer) {
//		return Integer.toBinaryString(Integer.valueOf(integer));	
//	}

	@Override
	public boolean getKill(TestResult original, double confidence) {
		return !map.equals(original.getResult()) || errorDetected;
	}

}
