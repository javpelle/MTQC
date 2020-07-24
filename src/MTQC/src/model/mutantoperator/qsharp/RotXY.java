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

public class RotXY extends QSharpGate {
	@Override
	public String getName() {
		return "RotX_RotY";
	}

	@Override
	public String getDescription() {
		return "Replace rotation over X axis with rotation over Y axis.";
	}

	@Override
	public String getSearchOperator() {
		return "Rx(";
	}

	@Override
	public String getMutantOperator() {
		return "Ry(";
	}

}
