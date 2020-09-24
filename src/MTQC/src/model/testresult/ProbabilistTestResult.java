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
 * Concrete TestResult class which represents the results obtained from running
 * a "Montecarlo" type of test.
 * 
 * @author Javier & Luis
 *
 */
public class ProbabilistTestResult extends TestResult {

	/**
	 * Constructor for the class.
	 * 
	 * @param mutantName Name of the mutant.
	 * @param idTest     Identifier for test.
	 */
	public ProbabilistTestResult(String mutantName, int idTest, int shots) {
		super(mutantName, idTest, shots);
	}

	@Override
	public String getName() {
		return mutantName + "_" + Integer.toString(idTest);
	}

	/**
	 * Overrides the Object toString method.
	 */
	public String toString() {
		String ret = "";
		for (HashMap.Entry<String, Long> entry : map.entrySet()) {
			if (entry.getValue().longValue() == -1l || entry.getValue().longValue() == -2l) {
				errorDetected = true;
				return entry.getValue().longValue() == -1l ? "Timeout" : "Execution error";
			}
			ret += "(" + entry.getKey() + ", " + entry.getValue() + ", "
					+ String.valueOf((double) Math.round(entry.getValue() * 1000.0 / shots) / 10.0) + "%)";
		}
		return ret;
	}

	@Override
	public boolean getKill(TestResult original, double confidence) {
		if (errorDetected)
			return true;
		long max = 0;
		HashMap<String, Long> originalMap = original.getResult();

		for (HashMap.Entry<String, Long> entry : map.entrySet()) {
			if (entry.getValue().longValue() == -1l || entry.getValue().longValue() == -2l) {
				return true;
			}
			if (!originalMap.containsKey(entry.getKey())) {
				max = Math.max(max, entry.getValue());
			} else {
				max = Math.max(max, Math.abs(entry.getValue() - originalMap.get(entry.getKey())));
			}
		}

		for (HashMap.Entry<String, Long> entry : originalMap.entrySet()) {
			if (!map.containsKey(entry.getKey())) {
				max = Math.max(max, entry.getValue());
			}
		}
		double percentageByCount = 100.0 / shots;
		double confidenceCounts = confidence / percentageByCount;
		return max > confidenceCounts;
	}

}
