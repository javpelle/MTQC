/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.mutantoperator.qiskit;

import model.mutantoperator.MutantOperator;

/**
 * Class for Qiskit mutant operators.
 * 
 * @author Javier & Luis
 *
 */
public abstract class QiskitGate extends MutantOperator {

	@Override
	/**
	 * Not used for Qiskit Operators
	 */
	public boolean checkRegEx(String matcher) {
		return true;
	}

}
