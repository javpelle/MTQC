/**
 * This code is part of MTQC.
 * 
 * Copyright (c) 2020 Javier Pellejero, Luis Aguirre.
 * 
 * This code is licensed under the MIT License. You may obtain a copy 
 * of this license in the LICENSE file in the root directory of this source tree 
 * or at https://github.com/javpelle/MTQC/blob/master/LICENSE.
 */

package model.language;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.files.TestFile;
import model.mutantoperator.MutantOperator;
import model.mutantoperator.qiskit.CCXCSWAPGate;
import model.mutantoperator.qiskit.CHSWAPGate;
import model.mutantoperator.qiskit.CHXGate;
import model.mutantoperator.qiskit.CHYGate;
import model.mutantoperator.qiskit.CHZGate;
import model.mutantoperator.qiskit.CSWAPCCXGate;
import model.mutantoperator.qiskit.CXHGate;
import model.mutantoperator.qiskit.CXSWAPGate;
import model.mutantoperator.qiskit.CXYGate;
import model.mutantoperator.qiskit.CXZGate;
import model.mutantoperator.qiskit.CYHGate;
import model.mutantoperator.qiskit.CYSWAPGate;
import model.mutantoperator.qiskit.CYXGate;
import model.mutantoperator.qiskit.CYZGate;
import model.mutantoperator.qiskit.CZHGate;
import model.mutantoperator.qiskit.CZSWAPGate;
import model.mutantoperator.qiskit.CZXGate;
import model.mutantoperator.qiskit.CZYGate;
import model.mutantoperator.qiskit.HXGate;
import model.mutantoperator.qiskit.HYGate;
import model.mutantoperator.qiskit.HZGate;
import model.mutantoperator.qiskit.RXYGate;
import model.mutantoperator.qiskit.RXZGate;
import model.mutantoperator.qiskit.RYXGate;
import model.mutantoperator.qiskit.RYZGate;
import model.mutantoperator.qiskit.RZXGate;
import model.mutantoperator.qiskit.RZYGate;
import model.mutantoperator.qiskit.SSdgGate;
import model.mutantoperator.qiskit.STGate;
import model.mutantoperator.qiskit.SWAPCHGate;
import model.mutantoperator.qiskit.SWAPCXGate;
import model.mutantoperator.qiskit.SWAPCYGate;
import model.mutantoperator.qiskit.SWAPCZGate;
import model.mutantoperator.qiskit.SZGate;
import model.mutantoperator.qiskit.SdgSGate;
import model.mutantoperator.qiskit.SdgTGate;
import model.mutantoperator.qiskit.SdgZGate;
import model.mutantoperator.qiskit.TSGate;
import model.mutantoperator.qiskit.TSdgGate;
import model.mutantoperator.qiskit.TZGate;
import model.mutantoperator.qiskit.XHGate;
import model.mutantoperator.qiskit.XYGate;
import model.mutantoperator.qiskit.XZGate;
import model.mutantoperator.qiskit.YHGate;
import model.mutantoperator.qiskit.YXGate;
import model.mutantoperator.qiskit.YZGate;
import model.mutantoperator.qiskit.ZHGate;
import model.mutantoperator.qiskit.ZSGate;
import model.mutantoperator.qiskit.ZSdgGate;
import model.mutantoperator.qiskit.ZTGate;
import model.mutantoperator.qiskit.ZXGate;
import model.mutantoperator.qiskit.ZYGate;
import model.testing.Testing;
import model.testresult.TestResult;

/**
 * Language concrete class, which overrides some methods in order to implement
 * the well behavior for Qiskit language.
 * 
 * @author Javier & Luis
 *
 */
public class Qiskit extends Language {

	/**
	 * Name of the inicialization method.
	 */
	private static final String method = "init";

	/**
	 * Initializes all possible mutant operators for Qiskit language.
	 */
	private MutantOperator[] qiskitOperators = { new CCXCSWAPGate(), new CHSWAPGate(), new CHXGate(), new CHYGate(),
			new CHZGate(), new CSWAPCCXGate(), new CXHGate(), new CXSWAPGate(), new CXYGate(), new CXZGate(),
			new CYHGate(), new CYSWAPGate(), new CYXGate(), new CYZGate(), new CZHGate(), new CZSWAPGate(),
			new CZXGate(), new CZYGate(), new HXGate(), new HYGate(), new HZGate(), new RXYGate(), new RXZGate(),
			new RYXGate(), new RYZGate(), new RZXGate(), new RZYGate(), new SdgSGate(), new SdgTGate(), new SdgZGate(),
			new SSdgGate(), new STGate(), new SWAPCHGate(), new SWAPCXGate(), new SWAPCYGate(), new SWAPCZGate(),
			new SZGate(), new TSdgGate(), new TSGate(), new TZGate(), new XHGate(), new XYGate(), new XZGate(),
			new YHGate(), new YXGate(), new YZGate(), new ZHGate(), new ZSdgGate(), new ZSGate(), new ZTGate(),
			new ZXGate(), new ZYGate() };

	/**
	 * Example test for Qiskit.
	 */
	private static final String qiskitInitText = "def init ():" + System.lineSeparator() + "\tcr = ClassicalRegister(1)"
			+ System.lineSeparator() + "\tqr = QuantumRegister(1)" + System.lineSeparator()
			+ "\tqc = QuantumCircuit(qr, cr)" + System.lineSeparator() + "" + System.lineSeparator()
			+ "\t# Initialize with desired quantum gates or QuantumCircuit.initialize() method" + System.lineSeparator()
			+ "" + System.lineSeparator();

	private static final String qiskitEndText = "\t# Add any operations if needed" + System.lineSeparator() + "\t"
			+ System.lineSeparator();

	@Override
	protected TestFile generateFile(String completePath, String fileName, String test, int id_test, String methodName,
			String mutantName) {
		String file = readFile(completePath);
		String newFileName = fileName.substring(0, fileName.length() - 3) + Integer.toString(id_test) + ".py";
		file = "from qiskit import *" + System.lineSeparator() + System.lineSeparator() + file + System.lineSeparator()
				+ test;
		writeFile(path + File.separator + newFileName, file);

		return new TestFile(mutantName, id_test, path, newFileName);
	}

	@Override
	protected String generateImportLanguage() {
		return "from qiskit import *" + System.lineSeparator();
	}
	
	@Override
	protected void generatePythonScript(ArrayList<ArrayList<TestFile>> files, Testing test, double timeLimit) {
		String script = generateImportLanguage();
		script += System.lineSeparator();
		script += "from tools import run_qiskit_shots, save_data" + System.lineSeparator();
		for (ArrayList<TestFile> list : files) {
			for (TestFile t : list) {
				script += "import " + t.getFileName() + System.lineSeparator();
			}
		}
		script += System.lineSeparator();
		script += "if __name__ == '__main__':" + System.lineSeparator();
		script += "\td = {}" + System.lineSeparator();
		for (ArrayList<TestFile> list : files) {
			for (TestFile t : list) {
				script += "\trun_qiskit_shots(" + getMethodCall(t.getFileName()) + ", " + String.valueOf(timeLimit) + ", "
						+ String.valueOf(test.getShots()) + ", " + "d)" + System.lineSeparator();
			}
		}
		script += "\tsave_data(d)" + System.lineSeparator();
		writeFile(path + File.separator + main, script);
	}
	
	@Override
	protected ArrayList<ArrayList<TestResult>> runMain(ArrayList<ArrayList<TestFile>> files, Testing test) {
		try {
			Process p = Runtime.getRuntime().exec(pythonCall(path, main));
			p.waitFor();
			return generateResults(files, test);
		} catch (IOException e) {

		} catch (InterruptedException e) {
			
		}
		return null;
	}

	private ArrayList<ArrayList<TestResult>> generateResults(ArrayList<ArrayList<TestFile>> files, Testing test) {
		ArrayList<ArrayList<TestResult>> results = new ArrayList<ArrayList<TestResult>>();
		for (ArrayList<TestFile> list : files) {
			ArrayList<TestResult> aux = new ArrayList<TestResult>();
			for (int t = 0; t < list.size(); t++) {
				TestResult tr = test.newTestResult(list.get(t).getMutantName(), list.get(t).getIdTest());
				for (int i = 0; i < test.getShots(); i++) {
					//tr.setResult(readLine(in));
				}
				tr.make();
				aux.add(tr);
			}
			results.add(aux);
			listener.notify("Test number " + (list.get(0).getIdTest() + 1) + " finished\n");
		}
		return results;
	}

	@Override
	protected String getMethodCall(String file) {
		return file + "." + method;
	}

	@Override
	protected String isQStateTest(Testing test) {
		return "";
	}

	@Override
	public MutantOperator[] getMutantOperators() {
		return qiskitOperators;
	}

	@Override
	public String getInputExample(Testing testing, int shots, String methodName) {
		String input = qiskitInitText;
		String end = "";
		if (methodName != null) {
			input += "\t" + methodName + System.lineSeparator() + System.lineSeparator();;
		} else {
			input += "\t# Call your method" + System.lineSeparator() + System.lineSeparator();
		}
		if (testing != null) {
			input += "\t" + testing.getQiskitSimulator(shots) + System.lineSeparator();
			end += "\t" + testing.getQiskitCounts() + System.lineSeparator();
		} else {
			input += "\tex = execute(qc, backend = Aer.get_backend('statevector_simulator'))" + System.lineSeparator();
			end = "\treturn pow(abs(ex.result().get_statevector()), 2)";
		}		
		return input + qiskitEndText + end;
	}

	@Override
	public String toString() {
		return "Qiskit";
	}

	@Override
	public String getExtension() {
		return ".py";
	}

	@Override
	public boolean verifyMatch(MutantOperator mutantOperator, String line, int offset, String searchWord) {
		return true;
	}

	@Override
	public String getStartMethodToken() {
		return "def ";
	}

	@Override
	public String getEndMethodToken() {
		return ":";
	}

}
