/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.mutantoperator.qsharp;

import model.mutantoperator.MutantOperator;

/**
 * Class for QSharp gate type mutant operator.
 * 
 * @author Javier & Luis
 *
 */
public abstract class QSharpGate extends MutantOperator {
	/**
	 * Especial regex verifier method for QSharp gate type operators
	 */
	public boolean checkRegEx(String matcher) {
		String noParenthesis = getSearchOperator().substring(0, getSearchOperator().length() - 1);
		return matcher.matches("\\W" + noParenthesis + ".*");
	}
}
