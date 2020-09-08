package model.mutantoperator.qiskit;

public class CCXForgotten extends QiskitGate {

	@Override
	public String getName() {
		return "GateCCX_Forgotten";
	}

	@Override
	public String getDescription() {
		return "Forget CCX Gate";
	}

	@Override
	public String getSearchOperator() {
		return ".ccx(";
	}

	@Override
	public String getMutantOperator() {
		return null;
	}

}
