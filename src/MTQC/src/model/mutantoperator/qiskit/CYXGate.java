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

public class CYXGate extends QiskitGate {
	@Override
	public String getName() {
		return "GateCY_GateCX";
	}

	@Override
	public String getDescription() {
		return "Replace CY gate with CX gate.";
	}

	@Override
	public String getSearchOperator() {
		return ".cy(";
	}

	@Override
	public String getMutantOperator() {
		return ".cx(";
	}

}