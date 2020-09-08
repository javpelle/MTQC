package model.mutantoperator.qiskit;

public class CHForgotten extends QiskitGate {

	@Override
	public String getName() {
		return "GateCH_Forgotten";
	}

	@Override
	public String getDescription() {
		return "Forget controlled H Gate";
	}

	@Override
	public String getSearchOperator() {
		return ".ch()";
	}

	@Override
	public String getMutantOperator() {
		return null;
	}

}
