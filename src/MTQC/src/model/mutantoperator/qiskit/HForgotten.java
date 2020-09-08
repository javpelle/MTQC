package model.mutantoperator.qiskit;

public class HForgotten extends QiskitGate {

	@Override
	public String getName() {
		return "GateH_Forgotten";
	}

	@Override
	public String getDescription() {
		return "Forget H Gate";
	}

	@Override
	public String getSearchOperator() {
		return ".h(";
	}

	@Override
	public String getMutantOperator() {
		return null; // If null remove line
	}
	
}
