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

public class CZYGate extends QiskitGate {
	@Override
	public String getName() {
		return "GateCZ_GateCY";
	}

	@Override
	public String getDescription() {
		return "Replace CZ gate with CY gate.";
	}

	@Override
	public String getSearchOperator() {
		return ".cz(";
	}

	@Override
	public String getMutantOperator() {
		return ".cy(";
	}

}