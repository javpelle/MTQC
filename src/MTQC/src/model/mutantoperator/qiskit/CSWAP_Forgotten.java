package model.mutantoperator.qiskit;

public class CSWAP_Forgotten extends QiskitGate {

	@Override
	public String getName() {
		return "GateCSWAP_Forgotten";
	}

	@Override
	public String getDescription() {
		return "Forgot controlled SWAP gate";
	}

	@Override
	public String getSearchOperator() {
		return ".cswap(";
	}

	@Override
	public String getMutantOperator() {
		return null;
	}

}
