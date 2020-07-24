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

import java.util.ArrayList;
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
	 * A list of all the results obtained from each shot.
	 */
	private ArrayList<String> result;
	/**
	 * Represents the ocurrences of each result.
	 */
	private HashMap<String, Integer> map;

	/**
	 * Constructor for the class.
	 * 
	 * @param mutantName Name of the mutant.
	 * @param idTest     Identifier for test.
	 */
	public ProbabilistTestResult(String mutantName, int idTest) {
		super(mutantName, idTest);
		result = new ArrayList<String>();
	}
	
	@Override
	public String getName() {
		return mutantName + "_" + Integer.toString(idTest);
	}

	@Override
	public void setResult(String result) {
		this.result.add(result);
	}

	@Override
	public void make() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String r : result) {
			if (map.containsKey(r)) {
				map.put(r, map.get(r) + 1);
			} else {
				map.put(r, 1);
			}
		}
		this.map = map;
	}
	
	/**
	 * Overrides the Object toString method.
	 */
	public String toString() {
		String ret = "";
		for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
			ret += "(" + entry.getKey() + ", " + entry.getValue() + ", "
					+ String.valueOf((double) Math.round(entry.getValue() * 1000.0 / result.size()) / 10.0) + "%)";
		}
		return ret;
	}

	@Override
	public boolean getKill(TestResult original, double confidence) {
		int max = 0;
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> originalMap = (HashMap<String, Integer>) original.getResult();

		for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
			if (!originalMap.containsKey(entry.getKey())) {
				max = Math.max(max, entry.getValue());
			} else {
				max = Math.max(max, Math.abs(entry.getValue() - originalMap.get(entry.getKey())));
			}
		}

		for (HashMap.Entry<String, Integer> entry : originalMap.entrySet()) {
			if (!map.containsKey(entry.getKey())) {
				max = Math.max(max, entry.getValue());
			}
		}
		double percentageByCount = 100.0 / result.size();
		double confidenceCounts = confidence / percentageByCount;
		return max > confidenceCounts;
	}

	@Override
	protected Object getResult() {
		return map;
	}

}
