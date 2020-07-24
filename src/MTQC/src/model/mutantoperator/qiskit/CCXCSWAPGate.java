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

public class CCXCSWAPGate extends QiskitGate {
	@Override
	public String getName() {
		return "GateCCX_GateCSWAP";
	}

	@Override
	public String getDescription() {
		return "Replace CCX gate with CSWAP gate.";
	}

	@Override
	public String getSearchOperator() {
		return ".ccx(";
	}

	@Override
	public String getMutantOperator() {
		return ".cswap(";
	}

}