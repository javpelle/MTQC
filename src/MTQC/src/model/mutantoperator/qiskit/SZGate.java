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

public class SZGate extends QiskitGate {
	@Override
	public String getName() {
		return "GateS_GateZ";
	}

	@Override
	public String getDescription() {
		return "Replace S gate with Z gate.";
	}

	@Override
	public String getSearchOperator() {
		return ".s(";
	}

	@Override
	public String getMutantOperator() {
		return ".z(";
	}

}