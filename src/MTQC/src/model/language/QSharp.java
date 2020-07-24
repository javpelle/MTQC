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

import java.io.File;
import java.util.ArrayList;

import model.files.TestFile;
import model.mutantoperator.MutantOperator;
import model.mutantoperator.qsharp.GateHX;
import model.mutantoperator.qsharp.GateHY;
import model.mutantoperator.qsharp.GateHZ;
import model.mutantoperator.qsharp.GateST;
import model.mutantoperator.qsharp.GateTS;
import model.mutantoperator.qsharp.GateXH;
import model.mutantoperator.qsharp.GateXY;
import model.mutantoperator.qsharp.GateXZ;
import model.mutantoperator.qsharp.GateYH;
import model.mutantoperator.qsharp.GateYX;
import model.mutantoperator.qsharp.GateYZ;
import model.mutantoperator.qsharp.GateZH;
import model.mutantoperator.qsharp.GateZX;
import model.mutantoperator.qsharp.GateZY;
import model.mutantoperator.qsharp.MissAdjoint;
import model.mutantoperator.qsharp.OneZero;
import model.mutantoperator.qsharp.PauliXY;
import model.mutantoperator.qsharp.PauliXZ;
import model.mutantoperator.qsharp.PauliYX;
import model.mutantoperator.qsharp.PauliYZ;
import model.mutantoperator.qsharp.PauliZX;
import model.mutantoperator.qsharp.PauliZY;
import model.mutantoperator.qsharp.RotXY;
import model.mutantoperator.qsharp.RotXZ;
import model.mutantoperator.qsharp.RotYX;
import model.mutantoperator.qsharp.RotYZ;
import model.mutantoperator.qsharp.RotZX;
import model.mutantoperator.qsharp.RotZY;
import model.mutantoperator.qsharp.ZeroOne;
import model.testing.QStateTesting;
import model.testing.Testing;

/**
 * Language concrete class, which overrides some methods in order to implement
 * the well behavior for QSharp language.
 * 
 * @author Javier & Luis
 *
 */
public class QSharp extends Language {
	
	/**
	 * Name for the initialization method.
	 */
	private static final String method = "MainQuantum";
	
	/**
	 * File to store results when running a QSharp Probabilistic test.
	 */
	protected static final String qStateQsharpTempFile = "temp.txt";

	/**
	 * Initializes all possible mutant operators for QSharp language.
	 */
	private MutantOperator[] qsharpOperators = { new GateHX(), new GateHY(), new GateHZ(), new GateST(), new GateTS(),
			new GateXH(), new GateXY(), new GateXZ(), new GateYH(), new GateYX(), new GateYZ(), new GateZH(),
			new GateZX(), new GateZY(), new MissAdjoint(), new OneZero(), new PauliXY(), new PauliXZ(), new PauliYX(),
			new PauliYZ(), new PauliZX(), new PauliZY(), new RotXY(), new RotXZ(), new RotYX(), new RotYZ(),
			new RotZX(), new RotZY(), new ZeroOne() };

	/**
	 * Example test for QSharp.
	 */
	private static final String qSharpText = "operation MainQuantum() : //Define main function output type {"
			+ System.lineSeparator() + System.lineSeparator() + "\t//Select desired Qubit number to be used"
			+ System.lineSeparator() + "\tusing (register = Qubit[2]) {" + System.lineSeparator() + ""
			+ System.lineSeparator() + "\t\t//Inicialize variables and Qubits" + System.lineSeparator()
			+ System.lineSeparator() + "\t\t//Call method and save output" + System.lineSeparator()
			+ System.lineSeparator() + "\t\t//If probabilistic test chosen." + System.lineSeparator()
			+ "\t\t//DumpMachine(\"temp.txt\");" + System.lineSeparator() + System.lineSeparator()
			+ "\t\t//Reset all qubits to Zero state" + System.lineSeparator() + "\t\tResetAll(register);"
			+ System.lineSeparator() + "" + System.lineSeparator() + "\t\t//Return output" + System.lineSeparator()
			+ "\t}" + System.lineSeparator() + "}" + System.lineSeparator()
			+ "//Define any other operation if needed as input";

	@Override
	protected TestFile generateFile(String completePath, String fileName, String test, int id_test, String methodName,
			String mutantName) {

		StringBuilder fileBuilder = null;
		String file = readFile(completePath);
		String namespaceName = fileName.substring(0, fileName.length() - 3) + Integer.toString(id_test);
		file = changeNamespace(file, namespaceName);
		// String mainMethod = getMainMethod(methodName, test);
		// mainMethod = tabString(mainMethod);
		String mainMethod = tabString(test);
		fileBuilder = new StringBuilder(file);
		int mainPos = fileBuilder.lastIndexOf("}") - 1;
		fileBuilder.insert(mainPos, System.lineSeparator() + System.lineSeparator() + mainMethod);
		writeFile(path + File.separator + namespaceName + ".qs", fileBuilder.toString());

		return new TestFile(mutantName, id_test, path, namespaceName + ".qs");
	}

	/**
	 * Changes the name space of a QSharp file.
	 * 
	 * @param file
	 *            Name of the file.
	 * @param namespaceName
	 *            Name of the new namespace.
	 * @return String which contains the declaration of the namespace.
	 */
	private static String changeNamespace(String file, String namespaceName) {
		int ini = file.indexOf("namespace ");
		int end = file.indexOf("{", ini);
		return file.substring(0, ini) + "namespace " + namespaceName + file.substring(end);
	}

	/**
	 * Given a String, the method tabs after each line break.
	 * 
	 * @param input
	 *            String to be tabbed.
	 * @return The initial String with a new tab character after each line
	 *         break.
	 */
	private static String tabString(String input) {
		StringBuilder builder = new StringBuilder(input);
		builder.insert(0, "\t");

		int index = builder.indexOf(System.lineSeparator());
		while (index != -1) {
			builder.replace(index, index + System.lineSeparator().length(), System.lineSeparator() + "\t");
			index += (System.lineSeparator() + "\t").length();
			index = builder.indexOf(System.lineSeparator(), index);
		}
		return builder.toString();
	}

	@Override
	protected String generateImportLanguage() {
		return "import qsharp" + System.lineSeparator();
	}

	@Override
	protected String isQStateTest(Testing test) {
		if (test instanceof QStateTesting) {
			return ", QStateTest=True";
		} else {
			return "";
		}
	}

	@Override
	protected String getMethodCall(String file) {
		return file + "." + method + ".simulate";
	}

	@Override
	public MutantOperator[] getMutantOperators() {
		return qsharpOperators;
	}

	@Override
	public String getInputExample() {
		return qSharpText;
	}

	@Override
	public String toString() {
		return "Q#";
	}

	@Override
	public String getExtension() {
		return ".qs";
	}

	@Override
	public boolean verifyMatch(MutantOperator mutantOperator, String line, int offset, String searchWord) {
		return mutantOperator.checkRegEx(line.substring(offset - 1, offset + searchWord.length() + 1));
	}

	@Override
	public String getStartMethodToken() {
		return "operation ";
	}

	@Override
	public String getEndMethodToken() {
		return "{";
	}
	
	@Override
	protected void deleteFiles(ArrayList<ArrayList<TestFile>> files) {
		super.deleteFiles(files);
		deleteFile(path + File.separator + qStateQsharpTempFile);
	}
}
