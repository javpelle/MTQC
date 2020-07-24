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

public class PauliZX extends QSharpConstant {
	@Override
	public String getName() {
		return "PauliZ_X";
	}

	@Override
	public String getDescription() {
		return "Replace PauliZ constant with PauliX constant.";
	}

	@Override
	public String getSearchOperator() {
		return "PauliZ";
	}

	@Override
	public String getMutantOperator() {
		return "PauliX";
	}

}
